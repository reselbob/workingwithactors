namespace TemporalioSamples.BarryPeanutes.Activities;

using Temporalio.Activities;
using TemporalioSamples.ActivitySimple.Models;

public class CheckoutActivities
{
    private readonly MyDatabaseClient dbClient = new();

    // Activities can be methods that can access state
    [Activity]
    public Task<Purchase> Checkout(Purchase purchase)
    {
        return purchase;
    }
}