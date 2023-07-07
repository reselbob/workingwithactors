namespace BarrysPeanuts;

public class Purchase : IPurchase
{
    public Guid Id { get; }
    public Customer Customer { get; set; }
    public int PackageSize { get; set; }
    public double Price { get; set; }
    public int Quantity { get; set; }
    public double Total { get; }
    public CreditCard? CreditCard { get; set; }
    public Address? BillingAddress { get; set; }
    public Address? ShippingAddress { get; set; }
    public DateTime? PurchaseDate{get;set;}

    public Purchase(Customer customer, int packageSize, double price, int quantity){
        Id = new Guid();
        Customer = customer;
        PackageSize = packageSize;
        Price = price;
        Quantity = quantity;
        Total = Quantity * Price;
        CreditCard = null;
        BillingAddress = null;
        ShippingAddress = null;
        PurchaseDate = null;
    }
}

