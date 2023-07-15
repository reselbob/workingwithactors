package actor;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.PurchaseItem;

public class CheckOutActor extends AbstractBehavior<PurchaseItem>{
    public CheckOutActor(ActorContext<PurchaseItem> context) {
        super(context);
    }

    @Override
    public Receive<PurchaseItem> createReceive() {
        return newReceiveBuilder().
                onMessage(PurchaseItem.class, this::makePurchase)
                .build();
    }

    private Behavior<PurchaseItem> makePurchase(PurchaseItem msg){
        String response = "Checking out a purchase with ID: "
                + msg.getId() + "for a total of: "
                + msg.getTotal();
        System.out.println(response);
        return this;
    }

    public static Behavior<PurchaseItem> behavior(){
        return Behaviors.setup(CheckOutActor::new);
    }
}