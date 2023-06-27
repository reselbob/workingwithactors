package msg;

import java.util.UUID;
import java.util.Date;

public class Purchase {
    public Purchase(Customer customer,
                    int packageSize,
                    int quantity,
                    double price,
                    Address billingAddress,
                    Address shippingAddress,
                    CreditCard creditCard) {
        this.customer = customer;
        this.packageSize = packageSize;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.shipDate = null;
        this.creditCard = creditCard;
    }

    public UUID getId() {
        if (this.id == null) {
            // Create a new UUID
            this.id = UUID.randomUUID();
        }
        return this.id;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(int packageSize) {
        this.packageSize = packageSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return this.quantity * this.price;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    private UUID id;
    private Customer customer;
    private int  packageSize;
    private int quantity;
    private double price;
    private double total;
    private Address billingAddress;
    private Address shippingAddress;
    private Date shipDate;



    private CreditCard creditCard;
}
