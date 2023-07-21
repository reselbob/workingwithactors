package barryspeanuts.task;

import barryspeanuts.ShoppingCartWorkflow;
import barryspeanuts.model.PurchaseItem;

public class AddItemTaskImpl implements WorkflowTask {

    private final PurchaseItem purchaseItem;

    public AddItemTaskImpl(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        shoppingCartWorkflow.addItem(this.purchaseItem);
    }
}