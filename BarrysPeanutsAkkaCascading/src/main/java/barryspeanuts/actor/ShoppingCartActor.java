package barryspeanuts.actor;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.ArrayList;
import java.util.Date;


import barryspeanuts.msg.CreditCard;
import barryspeanuts.msg.Customer;
import barryspeanuts.msg.PurchaseItem;
import barryspeanuts.helper.MockHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShoppingCartActor extends AbstractBehavior<Object> {
    Logger logger = LoggerFactory.getLogger(ShoppingCartActor.class);

    private ShoppingCartActor(ActorContext<Object> context) {
        super(context);
        this.purchaseItems = new ArrayList<PurchaseItem>();
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
                .build();
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
        this.purchaseItems = new ArrayList<>();
        return this;
    }

    private Behavior<Object> handleCheckoutCart(CheckoutCart msg) {
        String str = String.format("ShoppingCart is starting a checkout of %s items a checkout at %s. \n", this.purchaseItems.toArray().length, new Date());
        logger.info(str);
        ActorRef<Object> checkoutActor = ActorSystem.create(CheckOutActor.create(), "checkoutActor");
        Customer customer = this.purchaseItems.get(0).getCustomer();
        CreditCard creditCard = MockHelper.getCreditCard(customer.getFirstName(), customer.getLastName());
        CheckOutActor.StartCheckout startCheckout = new CheckOutActor.StartCheckout(this.purchaseItems,creditCard,customer);

        checkoutActor.tell(startCheckout);
        return this;
    }

    ArrayList<PurchaseItem> purchaseItems;
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