namespace BarrysPeanuts;

using Temporalio.Activities;
using System;
using System.Reflection;

public class BizActivities
{

    [Activity]
    public Purchase Checkout(Purchase purchase)
    {
        purchase.PurchaseDate = DateTime.Now;
        Console.WriteLine("Workflow result for Checkout: {0}", purchase);
        return purchase;
    }

  [Activity]
    public PaymentReceipt Pay(Purchase purchase,
        CreditCard creditCard,
        Address billingAddress,
        Address shippingAddress)
    {
        
        
        var result  = new PaymentReceipt(new Guid(), purchase.Id, DateTime.Now, creditCard,new Guid());
        Console.WriteLine("Workflow result for Pay: {0}", result);
        return result;
    }


    [Activity]
    public ShippingReceipt Ship(Purchase purchase, string shipper)
    {
        var result  = new ShippingReceipt(new Guid(), purchase.Id , DateTime.Now,shipper);
        Console.WriteLine("Workflow result for Ship: {0}", result);
        return result;
    }

}