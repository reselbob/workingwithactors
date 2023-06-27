import actor.PayActor;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.ActorRef;
import msg.Address;
import msg.CreditCard;
import msg.Customer;
import msg.Purchase;

import java.math.BigInteger;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting Barry's Gourmet Peanuts");
        ActorRef<Purchase> payActor = ActorSystem.create(PayActor.behavior(), "payActor");

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

        Purchase purchase = new Purchase(customer, 5, 1,10.99, address, address);

        payActor.tell(purchase);
    }
}



