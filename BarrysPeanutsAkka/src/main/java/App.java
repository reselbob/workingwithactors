import actor.ShoppingCartActor;
import akka.actor.typed.ActorSystem;
import msg.Address;
import msg.Customer;
import msg.PurchaseItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeoutException;


public class App {
    public static void main(String[] args) throws InterruptedException, TimeoutException {
        Logger logger = LoggerFactory.getLogger(ShoppingCartActor.class);
        logger.info("Starting Barry's Gourmet Peanuts");
        Customer customer = helper.MockHelper.getCustomer();
        Address address = helper.MockHelper.getAddress();

        PurchaseItem purchase = new PurchaseItem(customer, "Barry's Gourmet Peanuts",5, 1,10.99, address, address);
        ShoppingCartActor.AddItem item = new ShoppingCartActor.AddItem(purchase);
        ActorSystem<Object> shoppingCartActor = ActorSystem.create(ShoppingCartActor.create(), "shoppingCartActor");
        shoppingCartActor.tell(item);
        shoppingCartActor.tell(item);
        shoppingCartActor.tell(item);
        ShoppingCartActor.CheckoutCart checkout = new ShoppingCartActor.CheckoutCart();
        shoppingCartActor.tell(checkout);
        //BEWARE!!! Calling EmptyCart() here can cause race conditions
        ShoppingCartActor.EmptyCart emptyCart = new ShoppingCartActor.EmptyCart();
        shoppingCartActor.tell(emptyCart);
   }
}



