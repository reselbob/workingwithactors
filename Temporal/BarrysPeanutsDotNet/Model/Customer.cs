namespace BarrysPeanuts;
public class Customer : ICustomer
{
    public Guid Id { get;}
    public string FirstName { get; set; }
    public string LastName { get; set; }
    public string Email { get; set; }
    public string Phone { get; set; }
    public Address Address { get; set; }

    public Customer(ICustomer customer)
    {
        Id = new Guid();
        FirstName = customer.FirstName;
        LastName = customer.LastName;
        Email = customer.Email;
        Phone = customer.Phone;
        Address = customer.Address;
    }

}