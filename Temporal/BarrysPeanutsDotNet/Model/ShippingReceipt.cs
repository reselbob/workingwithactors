namespace BarrysPeanuts;
public class ShippingReceipt : IShippingReceipt
{
    public Guid Id { get; }
    public Guid PurchaseId { get; set;}
    public DateTime ShipDate { get; }
    public string Shipper { get; set;}

    public ShippingReceipt(Guid purchaseId, string shipper)
    {
        Id = new Guid();
        PurchaseId = purchaseId;
        ShipDate = DateTime.Now;
        Shipper = shipper;
    }

}