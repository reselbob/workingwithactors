namespace BarrysPeanuts;

using Temporalio.Workflows;

[Workflow]
public class BizWorkflow
{
    [WorkflowRun]
    public async Task RunAsync(int maxAttempts = 5)
    {
        Purchase purchase = PurchaseHelper.GetPurchase();
        
        purchase =  await Workflow.ExecuteActivityAsync(
            (BizActivities act) => act.Checkout(purchase),
            new() { ScheduleToCloseTimeout = TimeSpan.FromMinutes(5) });
        
        CreditCard creditCard = PurchaseHelper.GetCreditCard(purchase);

        var paymentReceipt =  await Workflow.ExecuteActivityAsync(
            (BizActivities act) => act.Pay(purchase,creditCard,purchase.Customer.address,purchase.Customer.address),
            new() { ScheduleToCloseTimeout = TimeSpan.FromMinutes(5) });
        
        string shipper = PurchaseHelper.GetShipper();

         var shippingReceipt =  await Workflow.ExecuteActivityAsync(
            (BizActivities act) => act.Ship(purchase, shipper),
            new() { ScheduleToCloseTimeout = TimeSpan.FromMinutes(5) });  
    }
}