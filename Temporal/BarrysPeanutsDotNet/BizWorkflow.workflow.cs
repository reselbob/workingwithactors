namespace BarrysPeanuts;

using Temporalio.Workflows;

[Workflow]
public class BizWorkflow
{
    [WorkflowRun]
    public async Task<string> RunAsync(string name)
    {
        IPurchase purchase = PurchaseHelper.GetPurchase();
        
        return await Workflow.ExecuteActivityAsync(
            (BizActivities act) => act.Checkout(purchase),
            new() { ScheduleToCloseTimeout = TimeSpan.FromMinutes(5) });
    }
}