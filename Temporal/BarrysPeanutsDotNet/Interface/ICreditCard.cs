namespace BarrysPeanuts;

public interface ICreditCard
{
    public string Name { get; set; }

    public string Number { get; set; }

    public Address BillingAddress { get; set; }

    public int ExpirationMonth { get; set; }

    public int ExpirationYear { get; set; }

    public int SecurityCode { get; set; }
}