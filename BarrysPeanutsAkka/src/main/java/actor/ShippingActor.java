package actor;
import java.util.Date;
import java.text.SimpleDateFormat;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.PurchaseItem;


public class ShippingActor extends AbstractBehavior<PurchaseItem>{
    public ShippingActor(ActorContext<PurchaseItem> context) {
        super(context);
    }

    @Override
    public Receive<PurchaseItem> createReceive() {
        return newReceiveBuilder().
                onMessage(PurchaseItem.class, this::makePurchase)
                .build();
    }

    private String getNowString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    private Behavior<PurchaseItem> makePurchase(PurchaseItem msg){
        Date today = new Date();
        msg.setShipDate(today);
        System.out.println("Shipping purchase with ID: " + msg.getId() + " on: " + this.getNowString(today));
        return this;
    }

    public static Behavior<PurchaseItem> behavior(){
        return Behaviors.setup(ShippingActor::new);
    }
}