package actor;
import java.util.Date;
import java.text.SimpleDateFormat;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.Purchase;


public class ShipActor extends AbstractBehavior<Purchase>{
    public ShipActor(ActorContext<Purchase> context) {
        super(context);
    }

    @Override
    public Receive<Purchase> createReceive() {
        return newReceiveBuilder().
                onMessage(Purchase.class, this::makePurchase)
                .build();
    }

    private String getNowString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    private Behavior<Purchase> makePurchase(Purchase msg){
        Date today = new Date();
        msg.setShipDate(today);
        System.out.println("Shipping purchase with ID: " + msg.getId() + " on: " + this.getNowString(today));
        return this;
    }

    public static Behavior<Purchase> behavior(){
        return Behaviors.setup(ShipActor::new);
    }
}