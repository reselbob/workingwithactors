package actor;

import akka.actor.typed.Behavior;
import akka.actor.typed.ActorRef;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.PurchaseItem;

public class PaymentActor extends AbstractBehavior<PurchaseItem> {
    public PaymentActor(ActorContext<PurchaseItem> context) {
        super(context);
    }

    @Override
    public Receive<PurchaseItem> createReceive() {
        return newReceiveBuilder().
                onMessage(PurchaseItem.class, this::makePayment)
                .build();
    }

    private Behavior<PurchaseItem> makePayment(PurchaseItem msg) {
        System.out.println("Paying for a purchase with ID: " + msg.getId());
        //this.getContext().getSelf().getSender().tell(msg);
        return this;
    }

    public static Behavior<PurchaseItem> behavior() {
        return Behaviors.setup(PaymentActor::new);
    }
}
