package barryspeanuts;

import barryspeanuts.mock.mockHelper;
import barryspeanuts.model.PurchaseItem;

import io.temporal.client.WorkflowOptions;
import io.temporal.testing.TestWorkflowRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingCartWorkflowTest {

    @Rule
    public TestWorkflowRule testWorkflowRule =
            TestWorkflowRule.newBuilder()
                    .setWorkflowTypes(ShoppingCartWorkflowImpl.class)
                    .setDoNotStart(true)
                    .build();

    @Test
    public void testIntegrationGetGreeting() {
        testWorkflowRule.getWorker().registerActivitiesImplementations(new ShoppingActivitiesImpl());
        testWorkflowRule.getTestEnvironment().start();

        ShoppingCartWorkflow workflow =
                testWorkflowRule
                        .getWorkflowClient()
                        .newWorkflowStub(
                                ShoppingCartWorkflow.class,
                                WorkflowOptions.newBuilder().setTaskQueue(testWorkflowRule.getTaskQueue()).build());
                                workflow.startWorkflow();
        testWorkflowRule.getTestEnvironment().shutdown();
    }

    @Test
    public void testMockedGetGreeting() {
        PurchaseItem purchase = mockHelper.getPurchase();
        /* 
        BarrysPeanutsActivities formatActivities = mock(BarrysPeanutsActivities.class, withSettings().withoutAnnotations());
        when(formatActivities.checkOut(purchase).thenReturn(purchase);
        testWorkflowRule.getWorker().registerActivitiesImplementations(formatActivities);
        testWorkflowRule.getTestEnvironment().start();

        BarrysPeanutsWorkflow workflow =
                testWorkflowRule
                        .getWorkflowClient()
                        .newWorkflowStub(
                                BarrysPeanutsWorkflow.class,
                                WorkflowOptions.newBuilder().setTaskQueue(testWorkflowRule.getTaskQueue()).build());
        String greeting = workflow.getGreeting("World");
        assertEquals("Hello World!", greeting);
        */
        testWorkflowRule.getTestEnvironment().shutdown();
    }
}
