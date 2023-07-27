package actor;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import msg.*;

import java.util.Date;
import java.util.Vector;
public class ShoppingCartActor extends AbstractBehavior<Object> {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartActor.class);

    private ShoppingCartActor(ActorContext<Object> context) {
        super(context);
        this.purchaseItems = new Vector<PurchaseItem>();
    }

    public static Behavior<Object> create() {
        return Behaviors.setup(ShoppingCartActor::new);
    }

    public static Behavior<Object> behavior() {
        return Behaviors.setup(ShoppingCartActor::new);
    }

    @Override
    public Receive<Object> createReceive() {
        return newReceiveBuilder()
                .onMessage(AddItem.class, this::handleAddItem)
                .onMessage(RemoveItem.class, this::handleRemoveItem)
                .onMessage(EmptyCart.class, this::handleEmptyCart)
                .onMessage(CheckoutCart.class, this::handleCheckoutCart)
                .onMessage(ConfirmationMessage.class, this::handleConfirmationMessage)
                .build();
    }

    private Behavior<Object> handleConfirmationMessage(ConfirmationMessage msg) {
        logger.info(msg.getContent());
        return this;
    }

    private Behavior<Object> handleAddItem(AddItem msg) {
        this.purchaseItems.add(msg.purchaseItem);
        return this;
    }

    private Behavior<Object> handleRemoveItem(RemoveItem msg) {
        this.purchaseItems.remove(msg.purchaseItem);
        return this;
    }


    private Behavior<Object> handleEmptyCart(EmptyCart msg) {
        String str = String.format("ShoppingCart is emptying the cart of %s items a checkout at %s. \n ", this.purchaseItems.toArray().length, new Date());
        logger.info(str);
        this.purchaseItems = new Vector<PurchaseItem>();
        return this;
    }

    private Behavior<Object> handleCheckoutCart(CheckoutCart msg) {

        String str = String.format("%s is starting a checkout of %s items a checkout at %s. \n", ShoppingCartActor.class, this.purchaseItems.toArray().length, new Date());
        logger.info(str);

        // Tell the CheckOut Actor to check out
        ActorRef<Object> checkoutActor = ActorSystem.create(CheckOutActor.create(), "checkoutActor");
        CheckOutActor.StartCheckout startCheckout = new CheckOutActor.StartCheckout(this.purchaseItems);
        checkoutActor.tell(startCheckout);


        // Tell the Payment Actor to pay
        ActorRef<Object> paymentActor = ActorSystem.create(PaymentActor.create(), "paymentActor");
        Customer customer = this.purchaseItems.firstElement().getCustomer();
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        CreditCard creditCard = helper.MockHelper.getCreditCard(firstName, lastName);
        PaymentActor.PaymentInfo paymentInfo = new PaymentActor.PaymentInfo(customer, creditCard, this.purchaseItems);
        paymentActor.tell(paymentInfo);

        // Tell the Shipper to ship
        ActorRef<Object> shipperActor = ActorSystem.create(ShipperActor.create(), "shipperActor");
        String shipper = helper.MockHelper.getShipper();
        ShipperActor.ShipmentInfo shippingInfo = new ShipperActor.ShipmentInfo(shipper, this.purchaseItems);
        shipperActor.tell(shippingInfo);

        return this;
    }

    Vector<PurchaseItem> purchaseItems;
    public static class AddItem {
        public AddItem(PurchaseItem purchaseItem) {
            this.purchaseItem = purchaseItem;
        }

        public PurchaseItem getPurchaseItem() {
            return purchaseItem;
        }

        PurchaseItem purchaseItem;
    }

    public static class RemoveItem {
        public RemoveItem(PurchaseItem purchaseItem) {
            this.purchaseItem = purchaseItem;
        }

        public PurchaseItem getPurchaseItem() {
            return purchaseItem;
        }

        PurchaseItem purchaseItem;
    }

    public static class EmptyCart {
        public EmptyCart() {
            this.emptyCartDate = new Date();
        }
        public Date getEmptyCartDate() {
            return emptyCartDate;
        }
        Date emptyCartDate;
    }

    public static class CheckoutCart {
        public CheckoutCart() {
            this.checkoutCartDate = new Date();
        }
        public Date getEmptyCartDate() {
            return checkoutCartDate;
        }
        Date checkoutCartDate;
    }
    
}