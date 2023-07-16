package msg;

import java.util.UUID;

public class Payment {

    public Payment(Customer customer, CreditCard creditCard, double paymentAmount) {
        this.id = UUID.randomUUID();
        this.customer = customer;
        this.creditCard = creditCard;
        this.paymentAmount = paymentAmount;
    }

    public UUID getId() {
        return id;
    }
    public Customer getCustomer() {
        return customer;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    UUID id;
    Customer customer;
    CreditCard creditCard;
    double paymentAmount;
}
