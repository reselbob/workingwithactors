namespace BarrysPeanuts;
public class Address: IAddress
{
    public string Address1 { get; set; }
    
    public string Address2 { get; set; }

    public string City { get; set; }

    public string StateProvince { get; set; }

    public string ZipRegionCode { get; set; }

      public string Country { get; set; }

    public Address(string address1, string address2, string city, string stateProvince, string zipRegionCode, string country)
    {
        Address1 = address1;
        Address2 = address2;
        City = city;
        StateProvince = stateProvince;
        ZipRegionCode = zipRegionCode;
        Country = country;
    }
}
