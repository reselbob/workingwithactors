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
        return purchase;
    }

  [Activity]
    public PaymentReceipt Pay(Purchase purchase,
        CreditCard creditCard,
        Address billingAddress,
        Address shippingAddress)
    {
        return new PaymentReceipt(new Guid(), purchase.Id, DateTime.Now, creditCard,new Guid());
    }


    [Activity]
    public ShippingReceipt Ship(Purchase purchase, string shipper)
    {
        return new ShippingReceipt(new Guid(), purchase.Id , DateTime.Now,shipper);
    }

}