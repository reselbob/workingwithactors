namespace BarrysPeanuts;

public interface IPurchase
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
}