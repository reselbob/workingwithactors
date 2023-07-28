package actor;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class PaymentActor extends AbstractBehavior<Object> {
    Logger logger = LoggerFactory.getLogger(ShoppingCartActor.class);

    private PaymentActor(ActorContext<Object> context) {
        super(context);
    }

    public static Behavior<Object> create() {
        return Behaviors.setup(PaymentActor::new);
    }

    public static Behavior<Object> behavior() {
        return Behaviors.setup(PaymentActor::new);
    }

    @Override
    public Receive<Object> createReceive() {
        return newReceiveBuilder()
                .onMessage(PaymentInfo.class, this::handlePayment)
                .build();
    }
    private Behavior<Object> handlePayment(PaymentInfo msg) {
        CreditCard creditCard = msg.getCreditCard();

        double amount = 0;
        for(PurchaseItem item : msg.getPurchaseItems()){
            amount = amount + item.getTotal();
        }
        // Now pay
        String str = String.format("Paying with Credit Card for %s with Credit Card Number %s on %s for the amount of %s\n",
                creditCard.getNameOnCard(),
                creditCard.getCreditCardNumber(),
                new Date(),
                amount
        );
        logger.info(str);
       // send a receipt to the Customer
        PaymentActor.PaymentReceipt paymentReceipt = new PaymentActor.PaymentReceipt(msg.customer,new Date(), msg.creditCard.getCreditCardNumber(),amount);
        ActorSystem<Object> customerActor = ActorSystem.create(CustomerActor.create(), "customerActor");
        customerActor.tell(paymentReceipt);

        //Send a paid message back to CheckOut
        ActorSystem<Object> checkOutActor = ActorSystem.create(CheckOutActor.create(), "checkOutActor");
        CheckOutActor.Paid paid = new CheckOutActor.Paid(msg.getId(),msg.getPurchaseItems(),new Date());
        checkOutActor.tell(paid);
        return this;
    }

    public static class PaymentInfo {

        public PaymentInfo(Customer customer, CreditCard creditCard, Vector<PurchaseItem> purchaseItems) {
            this.id = UUID.randomUUID();
            this.customer = customer;
            this.creditCard = creditCard;
            this.purchaseItems = purchaseItems;
        }

        public UUID getId() {
            return id;
        }
        public Customer getCustomer() {
            return customer;
        }

        public Vector<PurchaseItem> getPurchaseItems() {
            return purchaseItems;
        }

        public CreditCard getCreditCard() {
            return creditCard;
        }

        public double getPaymentAmount() {
            return paymentAmount;
        }

        UUID id;
        Customer customer;

        Vector<PurchaseItem> purchaseItems;
        CreditCard creditCard;
        double paymentAmount;
    }
    public static class PaymentReceipt {
        PaymentReceipt(Customer customer, Date paymentDate, String creditCardNumber, double amount) {
            this.id = UUID.randomUUID();
            this.customer = customer;
            this.paymentDate = paymentDate;
            this.creditCardNumber = creditCardNumber;
            this.amount = amount;
        }

        public UUID getId() {
            return id;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Date getPaymentDate() {
            return paymentDate;
        }

        public String getCreditCardNumber() {
            return creditCardNumber;
        }

        public double getAmount() {
            return amount;
        }

        UUID id;
        Customer customer;
        Date paymentDate;
        String creditCardNumber;
        double amount;
    }

}
