package barryspeanuts.actor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import barryspeanuts.msg.PurchaseItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class ShipperActor extends AbstractBehavior<Object> {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartActor.class);

    private ShipperActor(ActorContext<Object> context) {
        super(context);
    }

    public static Behavior<Object> create() {
        return Behaviors.setup(ShipperActor::new);
    }

    public static Behavior<Object> behavior() {
        return Behaviors.setup(ShipperActor::new);
    }

    @Override
    public Receive<Object> createReceive() {
        return newReceiveBuilder()
                .onMessage(ShipmentInfo.class, this::handleShipment)
                .build();
    }


    private Behavior<Object> handleShipment(ShipmentInfo msg) {
        // Now ship

        Date shipDate = new Date();

        String str = String.format("%s is Shipping the purchase using Shipper: %s on %s.\n",
                ShipperActor.class,
                msg.getShipper(),
                shipDate);

        logger.info(str);
        return this;
    }

    public static class ShipmentInfo {

        public ShipmentInfo(String shipper, ArrayList<PurchaseItem> purchaseItems) {
            this.id = UUID.randomUUID();
            this.shipper = shipper;
            this.purchaseItems = purchaseItems;
        }

        public UUID getId() {
            return id;
        }

        public String getShipper() {
            return shipper;
        }

        public ArrayList<PurchaseItem> getPurchaseItems() {
            return purchaseItems;
        }

        UUID id;

        String shipper;

        ArrayList<PurchaseItem> purchaseItems;
    }

    public static class ShippingReceipt {

        public ShippingReceipt(String shipper, Vector<PurchaseItem> purchaseItems, Date shipDate) {
            this.shipper = shipper;
            this.purchaseItems = purchaseItems;
            this.shipDate = shipDate;
        }

        public String getShipper() {
            return shipper;
        }

        public Vector<PurchaseItem> getPurchaseItems() {
            return purchaseItems;
        }

        public Date getShipDate() {
            return shipDate;
        }

        String shipper;
        Vector<PurchaseItem> purchaseItems;
        Date shipDate;
    }

}