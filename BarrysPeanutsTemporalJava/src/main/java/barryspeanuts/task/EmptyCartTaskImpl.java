package barryspeanuts.task;

import barryspeanuts.ShoppingCartWorkflow;
import barryspeanuts.ShoppingCartWorkflowImpl;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;


public class EmptyCartTaskImpl implements WorkflowTask {

    private static final Logger logger = Workflow.getLogger(EmptyCartTaskImpl.class);
    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        String str = String.format("%s : is emptying the cart of %s", EmptyCartTaskImpl.class,shoppingCartWorkflow.queryPurchaseItems());
        logger.info(str);
        shoppingCartWorkflow.clearItems();
        str = String.format("%s : has emptied the cart which now has %s item", EmptyCartTaskImpl.class,shoppingCartWorkflow.queryPurchaseItems());
        logger.info(str);
    }
}