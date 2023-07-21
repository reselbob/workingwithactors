package barryspeanuts;

import barryspeanuts.task.WorkflowTask;
import io.temporal.workflow.Functions;
import io.temporal.workflow.QueueConsumer;
import io.temporal.workflow.WorkflowQueue;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.Vector;

import barryspeanuts.model.PurchaseItem;

public class ShoppingCartWorkflowImpl implements ShoppingCartWorkflow {
    private WorkflowQueue<WorkflowTask> queue = new WorkflowQueue<WorkflowTask>() {
        @Override
        public WorkflowTask take() {
            return null;
        }

        @Override
        public WorkflowTask cancellableTake() {
            return null;
        }

        @Override
        public WorkflowTask poll() {
            return null;
        }

        @Override
        public WorkflowTask peek() {
            return null;
        }

        @Override
        public WorkflowTask poll(Duration duration) {
            return null;
        }

        @Override
        public WorkflowTask cancellablePoll(Duration duration) {
            return null;
        }

        @Override
        public <R> QueueConsumer<R> map(Functions.Func1<? super WorkflowTask, ? extends R> func1) {
            return null;
        }

        @Override
        public boolean offer(WorkflowTask iWorkflowTask) {
            return false;
        }

        @Override
        public void put(WorkflowTask iWorkflowTask) {

        }

        @Override
        public void cancellablePut(WorkflowTask iWorkflowTask) {

        }

        @Override
        public boolean offer(WorkflowTask iWorkflowTask, Duration duration) {
            return false;
        }

        @Override
        public boolean cancellableOffer(WorkflowTask iWorkflowTask, Duration duration) {
            return false;
        }
    };

    @Override
    public void startWorkflow() {
        // Start a loop that keeps running until the workflow is canceled.
        System.out.println("Starting Workflow for Barry's Peanuts");
        while (true) {
            if (Workflow.getInfo().getHistoryLength() > 2000) {
                // do continue as new here
                System.out.println("Workflow history is less than 2000");
            }

            WorkflowTask task = this.queue.cancellablePoll(Duration.ofDays(30L * 24 * 60 * 60 * 1000));
            if (task == null) {
                System.out.println("Breaking task");
                break;
            }
            task.process(this);
        }
    }


    @Override
    public Vector<PurchaseItem> queryPurchaseItems() {
        return null;
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
    public void CheckOut() {

    }

    @Override
    public void Pay() {

    }

    @Override
    public void Ship() {

    }

    @Override
    public void EmptyCart() {

    }

    @Override
    public void exit() {

    }

    Vector<PurchaseItem> purchaseItems;
}
