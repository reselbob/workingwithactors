package barryspeanuts.task;

import barryspeanuts.ShoppingActivitiesImpl;
import barryspeanuts.ShoppingCartWorkflow;
import barryspeanuts.model.PurchaseItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddItemTaskImpl implements WorkflowTask {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingActivitiesImpl.class);

    private final PurchaseItem purchaseItem;

    public AddItemTaskImpl(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        logger.info("adding an purchaseItem");
        shoppingCartWorkflow.addItem(this.purchaseItem);
    }
}