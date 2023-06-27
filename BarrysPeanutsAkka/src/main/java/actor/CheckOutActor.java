package actor;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.Purchase;

public class CheckOutActor extends AbstractBehavior<Purchase>{
    public CheckOutActor(ActorContext<Purchase> context) {
        super(context);
    }

    @Override
    public Receive<Purchase> createReceive() {
        return newReceiveBuilder().
                onMessage(Purchase.class, this::makePurchase)
                .build();
    }

    private Behavior<Purchase> makePurchase(Purchase msg){
        String response = "Checking out a purchase with ID: "
                + msg.getId() + "for a total of: "
                + msg.getTotal();
        System.out.println(response);
        return this;
    }

    public static Behavior<Purchase> behavior(){
        return Behaviors.setup(CheckOutActor::new);
    }
}