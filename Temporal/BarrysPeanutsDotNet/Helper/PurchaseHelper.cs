namespace BarrysPeanuts;

public class PurchaseHelper
{
    static public IPurchase GetPurchase()
    {
        var address = new Address("123 Happy Street", "Apt 2", "AnyTown", "IA", "50122", "USA");
        var customer = new Customer("Jenny", "Smith", "jenny@smith.com", "3101112222", address);
        return new Purchase(customer, 3, 10.99, 2);
    }
}