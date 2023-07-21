package barryspeanuts.task;

import barryspeanuts.ShoppingCartWorkflow;

public class ShipTaskImpl implements WorkflowTask {

    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        System.out.println("I am shipping");
    }
}