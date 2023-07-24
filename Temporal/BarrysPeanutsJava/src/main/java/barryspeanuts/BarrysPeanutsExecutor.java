package barryspeanuts;

import barryspeanuts.helper.helper;
import barryspeanuts.model.PurchaseItem;
import io.temporal.client.*;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.Vector;

public class BarrysPeanutsExecutor {


    private static final Logger logger = LoggerFactory.getLogger(BarrysPeanutsExecutor.class);
    static final String TASK_QUEUE = "BarryPeanutsJava";
    static final String WORKFLOW_ID = TASK_QUEUE + "-" + UUID.randomUUID();


    @SuppressWarnings("CatchAndPrintStackTrace")
    public static void main(String[] args) {
        // gRPC stubs wrapper that talks to the local docker instance of temporal
        // service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        // client that can be used to start and signal workflows
        WorkflowClient client = WorkflowClient.newInstance(service);

        // worker factory that can be used to create workers for specific task queues
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Worker that listens on a task queue and hosts both workflow and activity
        // implementations.
        Worker worker = factory.newWorker(TASK_QUEUE);

        // Workflows are stateful. So you need a type to create instances.
        worker.registerWorkflowImplementationTypes(ShoppingCartWorkflowImpl.class);

        // Activities are stateless and thread safe. So a shared instance is used.
        ShoppingCartActivities shoppingCartActivities = new ShoppingActivitiesImpl();
        worker.registerActivitiesImplementations(shoppingCartActivities);

        // Start all workers created by this factory.
        factory.start();
        logger.info("Worker started for task queue: " + TASK_QUEUE);

        // now we can start running instances of our workflow - its state will be persisted
        WorkflowOptions options = WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).setWorkflowId(WORKFLOW_ID).build();
        ShoppingCartWorkflow wf = client.newWorkflowStub(ShoppingCartWorkflow.class,options);
        try {

            BatchRequest signalWithStartReq = client.newSignalWithStartRequest();
            signalWithStartReq.add(wf::startWorkflow);
            client.signalWithStart(signalWithStartReq);
            PurchaseItem purchaseItem = helper.getPurchase();
            wf.addItem(purchaseItem);
            wf.addItem(purchaseItem);
            wf.addItem(purchaseItem);
            wf.checkOut("We are checking out");
            wf.pay();
            wf.ship();

            //Vector<PurchaseItem> purchaseItems = wf.query("queryPurchaseItems",Vector<PurchaseItem>.class );
            Vector<PurchaseItem> purchaseItems = wf.queryPurchaseItems();
            String str = String.format("the count of purchase items  is %s", purchaseItems.toArray().length);
            logger.info(str);

        } catch (WorkflowException e) {
            // Expected
        }

        System.exit(0);
    }
}