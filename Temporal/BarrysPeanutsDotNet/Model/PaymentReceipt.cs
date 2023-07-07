namespace BarrysPeanuts;

public class PaymentReceipt: IPaymentReceipt{
    public Guid Id { get; }
    public Guid PurchaseId { get; set;}
    public DateTime PaymentDate { get; }
    public CreditCard CreditCard { get; set;}
    public Guid TransactionId { get;}

    public PaymentReceipt(Purchase purchase){
        Id = new Guid();
        PurchaseId = purchase.Id;
        PaymentDate = DateTime.Now;
        CreditCard = purchase.CreditCard;
        TransactionId = new Guid();
    }
}