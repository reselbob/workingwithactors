package barryspeanuts;
import barryspeanuts.model.Purchase;
import barryspeanuts.task.*;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.WorkflowQueue;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.Date;
import java.util.Vector;

import barryspeanuts.model.PurchaseItem;

public class ShoppingCartWorkflowImpl implements ShoppingCartWorkflow {
    private WorkflowQueue<WorkflowTask> queue = Workflow.newWorkflowQueue(1024);

    ActivityOptions activitiesOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(60))
            .build();

    // Activities;
    @Override
    public void holderMethod() {

    }

    @Override
    public void startWorkflow() {
        System.out.println("Starting Workflow for Barry's Peanuts");
        while (true) {
            if (Workflow.getInfo().getHistoryLength() > 2000) {
                // do continue as new here
                System.out.println("Workflow history greater than 2000");
            }

            WorkflowTask task = this.queue.cancellablePoll(Duration.ofDays(30));
            if (task == null) {
                System.out.println("Breaking task");
                break;
            }
            task.process(this);
        }
    }


    @Override
    public Vector<PurchaseItem> queryPurchaseItems() {
        return this.purchaseItems;
    }

    @Override
    public void addItem(PurchaseItem purchaseItem) {
        if (this.purchaseItems == null) {
            this.purchaseItems = new Vector<>();
        }
        this.purchaseItems.add(purchaseItem);
    }

    @Override
    public void removeItem(PurchaseItem purchaseItem) {
        if (this.purchaseItems != null) {
            this.purchaseItems.remove(purchaseItem);
        }
    }

    public void clearItems() {
        this.purchaseItems = new Vector<>();
    }

    @Override
    public void checkOut() {
        this.queue.put(new CheckOutTaskImpl(this.getPurchase()));
    }

    @Override
    public void pay() {
        this.queue.put(new PayTaskImpl(this.getPurchase()));
    }

    @Override
    public void ship() {
        this.queue.put(new ShipTaskImpl(this.getPurchase(), "FEDEX"));
    }

    @Override
    public void emptyCart() {
        this.queue.put(new EmptyCartTaskImpl());
    }

    Purchase getPurchase(){
        Vector<PurchaseItem> items = queryPurchaseItems();
        return new Purchase(items, new Date());

    }

    Vector<PurchaseItem> purchaseItems;




}
