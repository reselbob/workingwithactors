namespace BarrysPeanuts;

using Temporalio.Activities;

public class BizActivities
{
    [Activity]
    public Purchase Checkout(Purchase purchase)
    {
        return purchase;
    }

    [Activity]
    public Purchase Pay(Purchase purchase)
    {
        return purchase;
    }

    [Activity]
    public Purchase Ship(Purchase purchase)
    {
        return purchase;
    }

}