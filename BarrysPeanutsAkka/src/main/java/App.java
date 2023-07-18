import actor.CustomerActor;
import actor.ShoppingCartActor;
import akka.actor.Props;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import msg.Address;
import msg.CreditCard;
import msg.Customer;
import msg.PurchaseItem;
import scala.concurrent.Await;

import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.math.BigDecimal;


import java.util.concurrent.TimeoutException;


public class App {
    public static void main(String[] args) throws InterruptedException, TimeoutException {
        System.out.println("Starting Barry's Gourmet Peanuts");
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
        //TODO figure out away to call back to the Shopping Cart when the actual Checkout completes
        ShoppingCartActor.EmptyCart emptyCart = new ShoppingCartActor.EmptyCart();
        shoppingCartActor.tell(emptyCart);
;    }
}



