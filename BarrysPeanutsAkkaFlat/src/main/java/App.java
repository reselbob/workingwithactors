import actor.PaymentActor;
import actor.ShipperActor;
import actor.ShoppingCartActor;
import akka.actor.typed.ActorSystem;
import msg.Address;
import msg.CreditCard;
import msg.Customer;
import msg.PurchaseItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeoutException;


public class App {
    public static void main(String[] args) throws InterruptedException, TimeoutException {
        Logger logger = LoggerFactory.getLogger(ShoppingCartActor.class);
        logger.info("Starting Barry's Gourmet Peanuts");

        Customer customer = helper.MockHelper.getCustomer();
        Address address = helper.MockHelper.getAddress();

        ArrayList<PurchaseItem> purchaseItems = new ArrayList<>();
        PurchaseItem purchase = new PurchaseItem(customer, "Barry's Gourmet Peanuts", 5, 1, 10.99, address, address);

        ShoppingCartActor.AddItem item = new ShoppingCartActor.AddItem(purchase);

        ActorSystem<Object> shoppingCartActor = ActorSystem.create(ShoppingCartActor.create(), "shoppingCartActor");
        purchaseItems.add(purchase);
        shoppingCartActor.tell(item);

        purchaseItems.add(purchase);
        shoppingCartActor.tell(item);

        purchaseItems.add(purchase);
        shoppingCartActor.tell(item);

        //Checkout
        ShoppingCartActor.CheckoutCart checkout = new ShoppingCartActor.CheckoutCart();
        shoppingCartActor.tell(checkout);

        // Pay
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        CreditCard creditCard = helper.MockHelper.getCreditCard(firstName, lastName);
        double totalAmount = purchaseItems.stream().mapToDouble(PurchaseItem::getTotal).sum();
        PaymentActor.PaymentInfo paymentInfo = new PaymentActor.PaymentInfo(customer, creditCard, totalAmount);
        shoppingCartActor.tell(paymentInfo);

        // Ship
        ShipperActor.ShipmentInfo shipmentInfo = new ShipperActor.ShipmentInfo(helper.MockHelper.getShipper(), purchaseItems);
        shoppingCartActor.tell(shipmentInfo);

        // Empty Cart
        ShoppingCartActor.EmptyCart emptyCart = new ShoppingCartActor.EmptyCart();
        shoppingCartActor.tell(emptyCart);
    }
}



