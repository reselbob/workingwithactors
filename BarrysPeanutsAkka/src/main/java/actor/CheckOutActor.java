package actor;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class CheckOutActor extends AbstractBehavior<CheckOutItems> {
    public CheckOutActor(ActorContext<CheckOutItems> context) {
        super(context);
    }

    @Override
    public Receive<CheckOutItems> createReceive() {
        return newReceiveBuilder().
                onMessage(CheckOutItems.class, this::makePurchase)
                .build();
    }

    private Behavior<CheckOutItems> makePurchase(CheckOutItems msg) {
        Vector<PurchaseItem> items = msg.getItems();
        double purchaseTotal = 0;
        for (PurchaseItem item : items) {
            purchaseTotal = purchaseTotal + item.getTotal();
        }
        Customer customer = items.elementAt(0).getCustomer();
        Payment payment = new Payment(customer,items.elementAt(0).getCreditCard(),purchaseTotal);
        ActorRef<Payment> paymentActor = ActorSystem.create(PaymentActor.behavior(), "payActor");
        paymentActor.tell(payment);
        PurchaseItem item = items.elementAt(0);
        PaymentReceipt receipt = new PaymentReceipt(item.getCustomer(),
                new Date(),
                item.getCreditCard().getCreditCardNumber(),
                purchaseTotal);
        return this;
    }

    private String getNowString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static Behavior<CheckOutItems> behavior() {
        return Behaviors.setup(CheckOutActor::new);
    }
}


