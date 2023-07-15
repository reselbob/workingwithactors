import actor.CheckOutActor;
import actor.PaymentActor;
import actor.ShippingActor;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.ActorRef;
import msg.Address;
import msg.CreditCard;
import msg.Customer;
import msg.PurchaseItem;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting Barry's Gourmet Peanuts");
        ActorRef<PurchaseItem> checkoutActor = ActorSystem.create(CheckOutActor.behavior(), "checkoutActor");
        ActorRef<PurchaseItem> paymentActor = ActorSystem.create(PaymentActor.behavior(), "payActor");
        ActorRef<PurchaseItem> shippingActor = ActorSystem.create(ShippingActor.behavior(), "shipActor");


        Address address = new Address("123 Main Street",
                "Apt 1",
                "Anytown",
                "CA",
                "99999-9999",
                "USA");

        Customer customer = new Customer("Barney",
                "Rubble",
                "barney@rubble.com",
                "310 878 9999", address);

        CreditCard creditCard = new CreditCard(customer.getFirstName() + " " + customer.getLastName(),
              "1111222233334444",8, 26,111 );

        PurchaseItem purchase = new PurchaseItem(customer, "Barry's Gourmet Peanuts",5, 1,10.99, address, address, creditCard);

        checkoutActor.tell(purchase);
        paymentActor.tell(purchase);
        shippingActor.tell(purchase);
    }
}



