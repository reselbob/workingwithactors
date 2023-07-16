import actor.CheckOutActor;
import actor.CustomerActor;
import actor.PaymentActor;
import actor.ShoppingCartActor;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.ActorRef;
import msg.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting Barry's Gourmet Peanuts");
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
        // customerActor.tell(purchase);
        ActorRef<ShoppingCartActor.Command> shoppingCartActor = ActorSystem.create(ShoppingCartActor.behavior(), "shoppingCartActor");
        ShoppingCartActor.Command command  = new ShoppingCartActor.Command(purchase,ShoppingCartActor.Command.Action.ADD);
        shoppingCartActor.tell(command);
        shoppingCartActor.tell(command);
        shoppingCartActor.tell(command);

        command  = new ShoppingCartActor.Command(purchase,ShoppingCartActor.Command.Action.CHECKOUT);
        shoppingCartActor.tell(command);


    }
}



