namespace BarrysPeanuts;

public record PaymentReceipt(Guid Id, Guid purchaseId, DateTime paymentDate, CreditCard creditCard, Guid transactionId);

public record ShippingReceipt(Guid id, Guid purchaseId, DateTime ShipDate, string Shipper);

public record Address(string address1, string address2, string city, string stateProvince, string zipRegionCode, string country);

public record CreditCard( string fullName, string number, int expirationMonth, int expirationYear, int securityCode);

public record Customer(Guid id,string firstName, string lastName,string email,string Phone,Address address);