package actor;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.Purchase;

public class PayActor extends AbstractBehavior<Purchase>{
    public PayActor(ActorContext<Purchase> context) {
        super(context);
    }

    @Override
    public Receive<Purchase> createReceive() {
        return newReceiveBuilder().
                onMessage(Purchase.class, this::makePurchase)
                .build();
    }

    private Behavior<Purchase> makePurchase(Purchase msg){
        System.out.println("Paying for a purchase with ID: " + msg.getId());
        return this;
    }

    public static Behavior<Purchase> behavior(){
        return Behaviors.setup(PayActor::new);
    }
}
