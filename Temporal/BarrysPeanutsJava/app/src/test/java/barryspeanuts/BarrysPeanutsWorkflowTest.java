package barryspeanuts;

import barryspeanuts.helper.helper;
import barryspeanuts.model.Purchase;

import io.temporal.client.WorkflowOptions;
import io.temporal.testing.TestWorkflowRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BarrysPeanutsWorkflowTest {

    @Rule
    public TestWorkflowRule testWorkflowRule =
            TestWorkflowRule.newBuilder()
                    .setWorkflowTypes(BarrysPeanutsWorkflowImpl.class)
                    .setDoNotStart(true)
                    .build();

    @Test
    public void testIntegrationGetGreeting() {
        testWorkflowRule.getWorker().registerActivitiesImplementations(new BarrysPeanutsActivitiesImpl());
        testWorkflowRule.getTestEnvironment().start();

        BarrysPeanutsWorkflow workflow =
                testWorkflowRule
                        .getWorkflowClient()
                        .newWorkflowStub(
                                BarrysPeanutsWorkflow.class,
                                WorkflowOptions.newBuilder().setTaskQueue(testWorkflowRule.getTaskQueue()).build());
                                Purchase purchase = helper.getPurchase();
                                workflow.getBizProcess(purchase);
        testWorkflowRule.getTestEnvironment().shutdown();
    }

    @Test
    public void testMockedGetGreeting() {
        Purchase purchase = helper.getPurchase();
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
