package actor;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.*;

import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class CheckOutActor extends AbstractBehavior<Object> {

    private CheckOutActor(ActorContext<Object> context) {
        super(context);
    }

    public static Behavior<Object> create() {
        return Behaviors.setup(CheckOutActor::new);
    }

    public static Behavior<Object> behavior() {
        return Behaviors.setup(CheckOutActor::new);
    }

    @Override
    public Receive<Object> createReceive() {
        return newReceiveBuilder()
                .onMessage(StartCheckout.class, this::handleStartCheckout)
                .onMessage(Pay.class, this::handlePay)
                .onMessage(Paid.class, this::handlePaid)
                .onMessage(ShipperActor.ShippingReceipt.class, this::handleShipped)
                .build();
    }

    private Behavior<Object> handleStartCheckout(StartCheckout msg) {
        // get the credit card number from the customer
        CustomerActor.CreditCardRequest creditCardRequest = new CustomerActor.CreditCardRequest(msg.getPurchaseItems());
        ActorSystem<Object> customerActor = ActorSystem.create(CustomerActor.create(), "customerActor");
        customerActor.tell(creditCardRequest);
        return this;
    }

    private Behavior<Object> handleShipped(ShipperActor.ShippingReceipt msg) {
        String str = String.format("Checkout says that I see the  purchase items items have shipped on %s via %s",
                msg.getShipDate(),
                msg.getShipper());
        System.out.println(str);

        return this;
    }

    private Behavior<Object> handlePay(Pay msg) {
        CreditCard creditCard = msg.getCreditCard();
        String str = String.format("Got Credit Card for %s with Credit Card Number %s on %s \n",
                creditCard.getNameOnCard(),
                creditCard.getCreditCardNumber(),
                new Date()
                );
        System.out.println(str);
        // Now pay
        double amount = 0;
        for(PurchaseItem item : msg.getPurchaseItems()){
            amount = amount + item.getTotal();
        }
        Customer customer = msg.getPurchaseItems().firstElement().getCustomer();
        PaymentActor.Payment  payment = new PaymentActor.Payment(customer,creditCard,msg.getPurchaseItems());
        ActorSystem<Object> paymentActor = ActorSystem.create(PaymentActor.create(), "paymentActor");
        paymentActor.tell(payment);
        return this;
    }

    private Behavior<Object> handlePaid(Paid msg) {
        String str = String.format("Got Paid for purchase with payment id %s on %sw  \n",
                msg.getPaymentId(),
                msg.getPaymentDate()
        );
        System.out.println(str);
        // Now ship the item
        ShipperActor.Shipment shipment = new ShipperActor.Shipment("FedEX", msg.getPurchaseItems());
        ActorSystem<Object> shipperActor = ActorSystem.create(ShipperActor.create(), "shipperActor");
        shipperActor.tell(shipment);
        return this;
    }

    public static class StartCheckout {
        public StartCheckout(Vector<PurchaseItem> purchaseItems) {
            this.purchaseItems = purchaseItems;
        }

        public Vector<PurchaseItem> getPurchaseItems() {
            return purchaseItems;
        }

        Vector<PurchaseItem> purchaseItems;

    }

    public static class Paid {
        public Paid(UUID paymentId, Vector<PurchaseItem> purchaseItems, Date paymentDate) {
            this.paymentId = paymentId;
            this.purchaseItems = purchaseItems;
            this.paymentDate = paymentDate;
        }

        public UUID getPaymentId() {
            return paymentId;
        }

        public Vector<PurchaseItem> getPurchaseItems() {
            return purchaseItems;
        }

        public Date getPaymentDate() {
            return paymentDate;
        }

        UUID paymentId;
        Vector<PurchaseItem> purchaseItems;
        Date paymentDate;

    }
    public static class Pay {
        public Pay(CreditCard creditCard, Vector<PurchaseItem> purchaseItems) {
            this.creditCard = creditCard;
            this.purchaseItems = purchaseItems;
        }

        public CreditCard getCreditCard() {
            return creditCard;
        }

        public Vector<PurchaseItem> getPurchaseItems() {
            return purchaseItems;
        }

        CreditCard creditCard;
        Vector<PurchaseItem> purchaseItems;
    }



}
