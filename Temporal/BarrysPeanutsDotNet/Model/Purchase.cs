namespace BarrysPeanuts;

public class Purchase : IPurchase
{
    public Guid Id { get; }
    public Customer Customer { get; set; }
    public int PackageSize { get; set; }
    public double Price { get; set; }
    public int Quantity { get; set; }
    public double Total { get; }
    public CreditCard CreditCard { get; set; }
    public Address BillingAddress { get; set; }
    public Address ShippingAddress { get; set; }

    public Purchase(IPurchase purchase){
        Id = new Guid();
        Customer = purchase.Customer;
        PackageSize = purchase.PackageSize;
        Price = purchase.Price;
        Quantity = purchase.Quantity;
        Total = Quantity * Price;
        CreditCard = purchase.CreditCard;
        BillingAddress = purchase.BillingAddress;
        ShippingAddress = purchase.ShippingAddress;
    }
}

