package barryspeanuts;


import barryspeanuts.model.Purchase;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface BarrysPeanutsWorkflow {
    @WorkflowMethod
    void getBizProcess(Purchase purchase);
}