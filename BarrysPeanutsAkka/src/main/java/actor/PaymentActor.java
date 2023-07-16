package actor;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.ActorRef;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.Payment;
import msg.PaymentReceipt;
import msg.PurchaseItem;

import java.time.LocalDateTime;
import java.util.Date;

public class PaymentActor extends AbstractBehavior<Payment> {
    public PaymentActor(ActorContext<Payment> context) {
        super(context);
    }

    @Override
    public Receive<Payment> createReceive() {
        return newReceiveBuilder().
                onMessage(Payment.class, this::makePayment)
                .build();
    }

    private Behavior<Payment> makePayment(Payment msg) {

        System.out.println("Paying for a purchase with ID: " + msg.getId());
        //this.getContext().getSelf().getSender().tell(msg);
        ActorRef<PaymentReceipt> customerActor = ActorSystem.create(CustomerActor.behavior(), "customerActor");
        PaymentReceipt receipt  = new PaymentReceipt(msg.getCustomer(), new Date(),
                msg.getCreditCard().getCreditCardNumber(),msg.getPaymentAmount());
        customerActor.tell(receipt);
        return this;
    }

    public static Behavior<Payment> behavior() {
        return Behaviors.setup(PaymentActor::new);
    }
}
