namespace BarrysPeanuts;

using Temporalio.Activities;
using System;
using System.Reflection;

public class BizActivities
{
    [Activity]
    public IPurchase Checkout(IPurchase purchase)
    {
        purchase.PurchaseDate = DateTime.Now;
        return purchase;
    }

    [Activity]
    public IPaymentReceipt Pay(IPurchase purchase,
        CreditCard creditCard,
        Address billingAddress,
        Address shippingAddress)
    {

        purchase.CreditCard = creditCard;
        purchase.BillingAddress = billingAddress;
        purchase.ShippingAddress = shippingAddress;
        return new PaymentReceipt(purchase as Purchase);
    }

    [Activity]
    public ShippingReceipt Ship(Purchase purchase, string shipper)
    {
        return new ShippingReceipt(purchase.Id, shipper);
    }

}