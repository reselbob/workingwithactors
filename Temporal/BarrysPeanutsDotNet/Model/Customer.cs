namespace BarrysPeanuts;
public class Customer : ICustomer
{
    public Guid Id { get;}
    public string FirstName { get; set; }
    public string LastName { get; set; }
    public string Email { get; set; }
    public string Phone { get; set; }
    public Address Address { get; set; }

    public Customer(string firstName, string lastName, string email, string phone, Address address)
    {
        Id = new Guid();
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Phone = phone;
        Address = address;
    }

}