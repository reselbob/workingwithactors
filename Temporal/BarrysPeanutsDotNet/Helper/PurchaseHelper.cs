namespace BarrysPeanuts;

public class PurchaseHelper
{
    static public Purchase GetPurchase()
    {
        var address = new Address("123 Happy Street", "Apt 2", "AnyTown", "IA", "50122", "USA");
        var customer = new Customer(new Guid(), "Jenny", "Smith", "jenny@smith.com", "3101112222", address);
        return new Purchase(customer, 3, 10.99m, 2);
    }

    static public CreditCard GetCreditCard(Purchase purchase)
    {
        string fullName = purchase.Customer.firstName + " " + purchase.Customer.lastName;
        return new CreditCard(fullName, "1111222233334444", 9, 2028, 999);
    }

    static public string GetShipper()
    {
        return "UPS";
    }
}