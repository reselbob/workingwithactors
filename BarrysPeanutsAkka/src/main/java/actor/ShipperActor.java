package actor;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.*;

import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class ShipperActor extends AbstractBehavior<Object> {

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
                .onMessage(Shipment.class, this::handleShipment)
                .build();
    }


    private Behavior<Object> handleShipment(Shipment msg) {
        // Now ship

        Date shipDate = new Date();

        String str = String.format("Shipping the purchase using Shipper: %s on %s.\n",
                msg.getShipper(),
                shipDate);

        System.out.println(str);
        // Send a shipping receipt back toa the Customer
        ShipperActor.ShippingReceipt shippingReceipt = new ShipperActor.ShippingReceipt(msg.getShipper(), msg.getPurchaseItems(),shipDate);
        ActorSystem<Object> customerActor = ActorSystem.create(CustomerActor.create(), "customerActor");
        customerActor.tell(shippingReceipt);

        //Send a shipping receipt back to CheckOut

        ActorSystem<Object> checkOutActor = ActorSystem.create(CheckOutActor.create(), "checkOutActor");
        CheckOutActor.Paid paid = new CheckOutActor.Paid(msg.getId(),msg.getPurchaseItems(),new Date());
        checkOutActor.tell(shippingReceipt);
        return this;
    }

    public static class Shipment {

        public Shipment(String shipper, Vector<PurchaseItem> purchaseItems) {
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

        public Vector<PurchaseItem> getPurchaseItems() {
            return purchaseItems;
        }

        UUID id;

        String shipper;

        Vector<PurchaseItem> purchaseItems;
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
