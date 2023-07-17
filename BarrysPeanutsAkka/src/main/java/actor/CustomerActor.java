package actor;
import java.util.Date;
import java.text.SimpleDateFormat;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.PaymentReceipt;
import msg.PurchaseItem;


public class CustomerActor extends AbstractBehavior<PaymentReceipt>{

    public CustomerActor(ActorContext<PaymentReceipt> context) {
        super(context);
    }

    @Override
    public Receive<PaymentReceipt> createReceive() {
        return newReceiveBuilder().
                onMessage(PaymentReceipt.class, this::processReceipt)
                .build();
    }


    private String getNowString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    private Behavior<PaymentReceipt> processReceipt(PaymentReceipt msg){
        String fullName = String.format("%s %s", msg.getCustomer().getFirstName(), msg.getCustomer().getLastName());
        String str = String.format("Customer %s processed Payment Receipt with ID: %s with CC Number: %s for the amount of: %s",
                fullName,
                msg.getId(),
                msg.getCreditCardNumber(),
                msg.getAmount());
        System.out.println(str);
        return this;
    }

    public static Behavior<PaymentReceipt> behavior(){
        return Behaviors.setup(CustomerActor::new);
    }
}
