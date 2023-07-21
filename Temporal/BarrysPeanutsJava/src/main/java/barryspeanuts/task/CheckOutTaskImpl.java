package barryspeanuts.task;

import barryspeanuts.ShoppingCartWorkflow;

public class CheckOutTaskImpl implements WorkflowTask {

    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        System.out.println("I am Checking Out");
    }
}