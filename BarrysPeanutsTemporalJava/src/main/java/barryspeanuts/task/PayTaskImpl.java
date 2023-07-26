package barryspeanuts.task;

import barryspeanuts.ShoppingActivitiesImpl;
import barryspeanuts.ShoppingCartActivities;
import barryspeanuts.ShoppingCartWorkflow;
import barryspeanuts.ShoppingCartWorkflowImpl;
import barryspeanuts.mock.mockHelper;
import barryspeanuts.model.CreditCard;
import barryspeanuts.model.Purchase;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;


public class PayTaskImpl implements WorkflowTask {
    private static final Logger logger = Workflow.getLogger(ShoppingCartWorkflowImpl.class);
    private final Purchase purchase;

    public PayTaskImpl(Purchase purchase) {
        this.purchase = purchase;
    }


    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        ShoppingCartActivities activities = new ShoppingActivitiesImpl();
        CreditCard creditCard = mockHelper.getCreditCard(this.purchase.getPurchaseItems().firstElement().getCustomer().getFirstName(),
                this.purchase.getPurchaseItems().firstElement().getCustomer().getLastName());
        String str = String.format("%s is Paying on CreditCard for %s", PayTaskImpl.class, creditCard.getFullName());
        logger.info(str);

        activities.pay(this.purchase,creditCard);
    }
}