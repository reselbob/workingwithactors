package barryspeanuts;

import barryspeanuts.helper.helper;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowException;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class BarrysPeanutsExecutor {

    static final String TASK_QUEUE = "BarryPeanutsJava";

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
        System.out.println("Worker started for task queue: " + TASK_QUEUE);

        // now we can start running instances of our workflow - its state will be persisted
        WorkflowOptions options = WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).build();
        ShoppingCartWorkflow wf = client.newWorkflowStub(ShoppingCartWorkflow.class, options);
        try {

            wf.startWorkflow();
        } catch (WorkflowException e) {
            // Expected
        }

        System.exit(0);
    }
}