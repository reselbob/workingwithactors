namespace BarrysPeanuts;

public interface ICustomer{
    public Guid Id { get;}
    public string FirstName { get; set; }
     public string LastName { get; set; }
     public string Email { get; set; }
     public string Phone { get; set; }
     public Address Address { get; set; }
    
} 