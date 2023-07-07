namespace BarrysPeanuts;

using System;

public interface IShippingReceipt{
    public Guid Id { get; }
    public Guid PurchaseId { get; set;}
    public DateTime ShipDate { get; }
    public string Shipper { get; set;}

}