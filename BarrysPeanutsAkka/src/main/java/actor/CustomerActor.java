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
import msg.PurchaseItem;
import actor.ShoppingCartActor;


public class CustomerActor extends AbstractBehavior<PurchaseItem>{

    public CustomerActor(ActorContext<PurchaseItem> context) {
        super(context);
    }

    @Override
    public Receive<PurchaseItem> createReceive() {
        return newReceiveBuilder().
                onMessage(PurchaseItem.class, this::sendToShoppingCart)
                .build();
    }

    private String getNowString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    private Behavior<PurchaseItem> sendToShoppingCart(PurchaseItem msg){
        Date today = new Date();
        System.out.println("CustomerActor sending purchase with ID: " + msg.getId() + " to ShoppingCart: " + this.getNowString(today));
        ActorRef<ShoppingCartActor.Command> shoppingCartActor = ActorSystem.create(ShoppingCartActor.behavior(), "shoppingCartActor");
        ShoppingCartActor.Command command  = new ShoppingCartActor.Command(msg,ShoppingCartActor.Command.Action.ADD);
        shoppingCartActor.tell(command);
        return this;
    }

    public static Behavior<PurchaseItem> behavior(){
        return Behaviors.setup(ShippingActor::new);
    }
}
