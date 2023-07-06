namespace BarrysPeanuts;
public class Address: IAddress
{
    public string Address1 { get; set; }
    
    public string Address2 { get; set; }

    public string City { get; set; }

    public string StateProvince { get; set; }

    public string ZipRegionCode { get; set; }

      public string Country { get; set; }

    public Address(IAddress address)
    {
        Address1 = address.Address1;
        Address2 = address.Address2;
        City = address.City;
        StateProvince = address.StateProvince;
        ZipRegionCode = address.ZipRegionCode;
        Country = address.Country;
    }
}
