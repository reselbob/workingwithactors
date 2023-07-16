package msg;

import msg.Customer;

import java.util.Date;
import java.util.UUID;

public class PaymentReceipt {
    public PaymentReceipt(Customer customer, Date paymentDate, String creditCardNumber, double amount) {
        this.id = UUID.randomUUID();
        this.customer = customer;
        this.paymentDate = paymentDate;
        this.creditCardNumber = creditCardNumber;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public double getAmount() {
        return amount;
    }

    UUID id;
    Customer customer;
    Date paymentDate;
    String creditCardNumber;
    double amount;
}
