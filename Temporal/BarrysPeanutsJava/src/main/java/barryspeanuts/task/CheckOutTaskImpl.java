package barryspeanuts.task;

import barryspeanuts.ShoppingActivitiesImpl;
import barryspeanuts.ShoppingCartActivities;
import barryspeanuts.ShoppingCartWorkflow;
import barryspeanuts.model.Purchase;


public class CheckOutTaskImpl implements WorkflowTask {

    private final Purchase purchase;

    public CheckOutTaskImpl(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        ShoppingCartActivities activities = new ShoppingActivitiesImpl();
        String fullName = String.format("%s %s", shoppingCartWorkflow.queryPurchaseItems().firstElement().getCustomer().getFirstName(),
                shoppingCartWorkflow.queryPurchaseItems().firstElement().getCustomer().getLastName());
        String str = String.format("%s : is checking out customer %s", CheckOutTaskImpl.class,fullName);
        System.out.println(str);
        activities.checkOut(this.purchase.getPurchaseItems());
    }
}