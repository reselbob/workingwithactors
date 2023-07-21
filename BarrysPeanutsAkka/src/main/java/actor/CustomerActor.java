package actor;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import helper.MockHelper;
import msg.CreditCard;
import msg.Customer;
import msg.PurchaseItem;
import java.util.Date;
import java.util.Vector;

public class CustomerActor extends AbstractBehavior<Object> {

    private CustomerActor(ActorContext<Object> context) {
        super(context);
    }

    public static Behavior<Object> create() {
        return Behaviors.setup(CustomerActor::new);
    }

    public static Behavior<Object> behavior() {
        return Behaviors.setup(CustomerActor::new);
    }

    @Override
    public Receive<Object> createReceive() {
        return newReceiveBuilder()
                .onMessage(PaymentActor.PaymentReceipt.class, this::handlePaymentReceipt)
                .onMessage(ShipperActor.ShippingReceipt.class, this::handleShippingReceipt)
                .onMessage(CreditCardRequest.class, this::handleCreditCardRequest)
                .build();
    }

    private Behavior<Object> handlePaymentReceipt(PaymentActor.PaymentReceipt msg) {
        String fullName = String.format("%s %s", msg.getCustomer().getFirstName(), msg.getCustomer().getLastName());
        String str = String.format("Customer %s processed PaymentInfo Receipt with ID: %s on %s with CC Number: %s for the amount of: %s",
                fullName,
                msg.getId(),
                msg.getPaymentDate(),
                msg.getCreditCardNumber(),
                msg.getAmount());
        System.out.println(str);
        return this;
    }

    private Behavior<Object> handleShippingReceipt(ShipperActor.ShippingReceipt msg) {
        Date today = new Date();
        Vector<PurchaseItem> items = msg.getPurchaseItems();
        String firstName = null;
        String lastName = null;
        String shipper = msg.getShipper();
        Date shipDate = new Date();
        for(PurchaseItem item : items){
            firstName = item.getCustomer().getFirstName();
            lastName = item.getCustomer().getLastName();
            item.setShipDate(shipDate);
        }
        String str = String.format("Shipped %s purchases to %s %s using %s. \n",
                items.toArray().length,
                firstName,
                lastName,
                shipper);
        System.out.println(str);
        return this;
    }

    private Behavior<Object> handleCreditCardRequest(CreditCardRequest msg) {
        Customer customer = msg.getPurchaseItems().firstElement().getCustomer();
        CreditCard mockCreditCard = MockHelper.getCreditCard(customer.getFirstName(), customer.getLastName());
        ActorSystem<Object> checkOutActor = ActorSystem.create(CheckOutActor.create(), "checkOutActor");
        CheckOutActor.Pay pay = new CheckOutActor.Pay(mockCreditCard,msg.getPurchaseItems());
        //Send the information back to the CheckOutActor
        checkOutActor.tell(pay);
        return this;
    }

    public static class CreditCardRequest {
        Vector<PurchaseItem> purchaseItems;
        public CreditCardRequest(Vector<PurchaseItem> purchaseItems) {
            this.purchaseItems = purchaseItems;
        }
        public Vector<PurchaseItem> getPurchaseItems() {
            return this.purchaseItems;
        }
    }
}
