namespace BarrysPeanuts;

using System;

public interface IPaymentReceipt{
    public Guid Id { get; }
    public Guid PurchaseId { get; set;}
    public DateTime PaymentDate { get; }
    public CreditCard CreditCard { get; set;}
    public Guid TransactionId { get;}

}