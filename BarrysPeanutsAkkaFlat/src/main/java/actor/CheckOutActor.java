package actor;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class CheckOutActor extends AbstractBehavior<Object> {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartActor.class);

    private CheckOutActor(ActorContext<Object> context) {
        super(context);
    }

    public static Behavior<Object> create() {
        return Behaviors.setup(CheckOutActor::new);
    }

    public static Behavior<Object> behavior() {
        return Behaviors.setup(CheckOutActor::new);
    }

    @Override
    public Receive<Object> createReceive() {
        return newReceiveBuilder()
                .onMessage(StartCheckout.class, this::handleStartCheckout)
                .build();
    }

    private Behavior<Object> handleStartCheckout(StartCheckout msg) {
        String str = String.format("%s has has started Checkout\n", CheckOutActor.class);
        logger.info(str);
        return this;
    }


    public static class StartCheckout {
        public StartCheckout(ArrayList<PurchaseItem> purchaseItems) {
            this.purchaseItems = purchaseItems;
        }

        public ArrayList<PurchaseItem> getPurchaseItems() {
            return purchaseItems;
        }

        ArrayList<PurchaseItem> purchaseItems;

    }




}
