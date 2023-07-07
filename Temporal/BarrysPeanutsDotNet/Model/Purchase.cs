namespace BarrysPeanuts;

public class Purchase
{
    public Guid Id { get; }
    public Customer Customer { get; set; }
    public int PackageSize { get; set; }
    public decimal Price { get; set; }
    public int Quantity { get; set; }
    public decimal Total => Quantity * Price;
    public CreditCard? CreditCard { get; set; }
    public Address? BillingAddress { get; set; }
    public Address? ShippingAddress { get; set; }
    public DateTime? PurchaseDate{get;set;}

    public Purchase(Customer customer, int packageSize, decimal price, int quantity){
        Id = new Guid();
        Customer = customer;
        PackageSize = packageSize;
        Price = price;
    }
}

