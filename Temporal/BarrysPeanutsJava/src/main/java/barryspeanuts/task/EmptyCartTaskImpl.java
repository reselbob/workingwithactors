package barryspeanuts.task;

import barryspeanuts.ShoppingCartWorkflow;


public class EmptyCartTaskImpl implements WorkflowTask {
    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        System.out.println("I am Emptying the Cart");
        shoppingCartWorkflow.clearItems();
    }
}