package barryspeanuts.task;

import barryspeanuts.ShoppingActivitiesImpl;
import barryspeanuts.ShoppingCartActivities;
import barryspeanuts.ShoppingCartWorkflow;
import barryspeanuts.model.Purchase;

public class ShipTaskImpl implements WorkflowTask {
    private final Purchase purchase;
    private final String shipper;

    public ShipTaskImpl(Purchase purchase, String shipper) {
        this.shipper = shipper;
        this.purchase = purchase;
    }

    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        ShoppingCartActivities activities = new ShoppingActivitiesImpl();
        System.out.println("I am Shipping via " + shipper);
        activities.ship(this.purchase,this.shipper);
    }
}