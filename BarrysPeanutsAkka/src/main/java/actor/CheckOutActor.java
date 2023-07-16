package actor;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.PurchaseItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class CheckOutActor extends AbstractBehavior<CheckOutActor.CheckOutItems>{
    public CheckOutActor(ActorContext<CheckOutActor.CheckOutItems> context) {
        super(context);
    }

    @Override
    public Receive<CheckOutItems> createReceive() {
        return newReceiveBuilder().
                onMessage(CheckOutItems.class, this::makePurchase)
                .build();
    }

    private Behavior<CheckOutItems> makePurchase(CheckOutItems msg){
        Vector<PurchaseItem> items = msg.getItems();
        for (PurchaseItem item : items) {
            Date today = new Date();
            System.out.printf("Checking out Purchase Item: %s at %s \n", item.getId(),this.getNowString(today));
        }
        return this;
    }

    private String getNowString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    public static Behavior<CheckOutItems> behavior(){
        return Behaviors.setup(CheckOutActor::new);
    }
    public static class CheckOutItems {
        public CheckOutItems(Vector<PurchaseItem> items) {
            this.items = items;

        }

        public Vector<PurchaseItem> getItems() {
            return items;
        }

        public Vector<PurchaseItem> items;
    }
}

