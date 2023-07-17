package actor;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.CheckOutItems;
import msg.PurchaseItem;
import msg.ShipItem;
import msg.ShippingReceipt;


public class ShippingActor extends AbstractBehavior<ShipItem>{
    public ShippingActor(ActorContext<ShipItem> context) {
        super(context);
    }

    @Override
    public Receive<ShipItem> createReceive() {
        return newReceiveBuilder().
                onMessage(ShipItem.class, this::ship)
                .build();
    }

    private String getNowString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    private Behavior<ShipItem> ship(ShipItem msg){
        Date today = new Date();
        Vector<PurchaseItem> items = msg.getCheckOutItems().getItems();
        String firstName = null;
        String lastName = null;
        String shipper = msg.getShipper();
        Date shipDate = new Date();
        for(PurchaseItem item : items){
            firstName = item.getCustomer().getFirstName();
            lastName = item.getCustomer().getLastName();
            item.setShipDate(shipDate);
        }
        String str = String.format("Shipping %s purchases to %s %s using %s. \n",
                msg.getCheckOutItems().items.toArray().length,
                firstName,
                lastName,
                shipper);
        System.out.println(str);
        ShippingReceipt receipt  = new ShippingReceipt(shipper,msg.getCheckOutItems().items,shipDate);
        //TODO Put in the messaging to send the Shipping Receipt to th customer;
        return this;
    }

    public static Behavior<ShipItem> behavior(){
        return Behaviors.setup(ShippingActor::new);
    }
}