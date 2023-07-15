package barryspeanuts;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import barryspeanuts.helper.helper;
import barryspeanuts.model.PaymentReceipt;
import barryspeanuts.model.Purchase;
import barryspeanuts.model.ShippingReceipt;

public class BarrysPeanutsWorkflowImpl implements BarrysPeanutsWorkflow {
    ActivityOptions options = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofSeconds(60))
        .build();

    private final BarrysPeanutsActivities activity = Workflow.newActivityStub(BarrysPeanutsActivities.class, options);

    @Override
    public void getBizProcess(Purchase purchase) {
        purchase = activity.checkOut(purchase);
        PaymentReceipt payReceipt = activity.pay(purchase, helper.getCreditCard());
        ShippingReceipt shippingReceipt = activity.ship(purchase, "FedEX");
    }
}
