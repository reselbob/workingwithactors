package barryspeanuts.task;

import barryspeanuts.ShoppingActivitiesImpl;
import barryspeanuts.ShoppingCartActivities;
import barryspeanuts.ShoppingCartWorkflow;
import barryspeanuts.ShoppingCartWorkflowImpl;
import barryspeanuts.model.Purchase;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;


public class CheckOutTaskImpl implements WorkflowTask {

    private static final Logger logger = Workflow.getLogger(ShoppingCartWorkflowImpl.class);

    private final Purchase purchase;

    public CheckOutTaskImpl(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        ShoppingCartActivities activities = shoppingCartWorkflow.queryActivities();
        String fullName = String.format("%s %s", shoppingCartWorkflow.queryPurchaseItems().firstElement().getCustomer().getFirstName(),
                shoppingCartWorkflow.queryPurchaseItems().firstElement().getCustomer().getLastName());
        String str = String.format("%s : is checking out customer %s", CheckOutTaskImpl.class,fullName);
        logger.info(str);

        // call the activity
        activities.checkOut(this.purchase.getPurchaseItems());
    }
}