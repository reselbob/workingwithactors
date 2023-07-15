package actor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import msg.PurchaseItem;

public class ShoppingCartActor extends AbstractBehavior<ShoppingCartActor.Command> {
    private final Vector<PurchaseItem> items;

    public Vector<PurchaseItem> getItems() {
        return items;
    }

    public ShoppingCartActor(ActorContext<ShoppingCartActor.Command> context) {
        super(context);
        this.items = new Vector<PurchaseItem>();
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(Command.class, this::handleCommand)
                .build();
    }

    private String getNowString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    private Behavior<Command> handleCommand(Command command){
        Date today = new Date();

        if(Command.Action.ADD == command.getAction()){
            this.items.add(command.getPurchaseItem());
        }else{
            this.items.remove(command.getPurchaseItem());
        }

        return this;
    }

    public static Behavior<Command> behavior(){
        return (Behavior<ShoppingCartActor.Command>) Behaviors.setup(ShoppingCartActor::new);
    }

    public static class Command {
        public enum Action {
            ADD,
            REMOVE
        }

        public PurchaseItem getPurchaseItem() {
            return purchaseItem;
        }

        public Command(PurchaseItem purchaseItem, Action action) {
            this.purchaseItem = purchaseItem;
            this.action = action;
        }

        public PurchaseItem purchaseItem;

        Action action;

        public Action getAction() {
            return action;
        }
    }
}
