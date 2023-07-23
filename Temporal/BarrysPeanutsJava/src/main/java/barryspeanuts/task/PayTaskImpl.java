package barryspeanuts.task;

import barryspeanuts.ShoppingActivitiesImpl;
import barryspeanuts.ShoppingCartActivities;
import barryspeanuts.ShoppingCartWorkflow;
import barryspeanuts.helper.helper;
import barryspeanuts.model.CreditCard;
import barryspeanuts.model.Purchase;



public class PayTaskImpl implements WorkflowTask {

    private final Purchase purchase;

    public PayTaskImpl(Purchase purchase) {
        this.purchase = purchase;
    }


    @Override
    public void process(ShoppingCartWorkflow shoppingCartWorkflow) {
        ShoppingCartActivities activities = new ShoppingActivitiesImpl();
        CreditCard creditCard = helper.getCreditCard(this.purchase.getPurchaseItems().firstElement().getCustomer().getFirstName(),
                this.purchase.getPurchaseItems().firstElement().getCustomer().getLastName());
        String str = String.format("%s is Paying on CreditCard for %s", PayTaskImpl.class, creditCard.getFullName());
        System.out.println(str);
        activities.pay(this.purchase,creditCard);
    }
}