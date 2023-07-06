namespace BarrysPeanuts;

public class CreditCard: ICreditCard
{
    public string Name { get; set; }

    public string Number { get; set; }

    public Address BillingAddress { get; set; }

    public int ExpirationMonth { get; set; }

    public int ExpirationYear { get; set; }

    public int SecurityCode { get; set; }

    public CreditCard(ICreditCard creditCard)
    {
        Name = creditCard.Name;
        Number = creditCard.Number;
        BillingAddress = creditCard.BillingAddress;
        ExpirationMonth = creditCard.ExpirationMonth;
        ExpirationYear = creditCard.ExpirationYear;
        SecurityCode = creditCard.SecurityCode;
    }
}
