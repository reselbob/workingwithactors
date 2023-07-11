package barryspeanuts.model;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.Optional;

public class Purchase
{
    public UUID id;
    public Customer customer;
    public int packageSize;
    public double price;
    public int quantity;
    public double total;
    public Optional<CreditCard>creditCard;
    public Optional<Address> billingAddress;
    public Optional<Address> shippingAddress;
    public LocalDateTime purchaseDate;

    public Purchase(){}
    
    public Purchase(Customer customer, int packageSize, double price, int quantity){
        this.id = UUID.randomUUID();
        this.customer = customer;
        this.packageSize = packageSize;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getId() {
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
        return this.id;
    }


    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getPackageSize() {
        return this.packageSize;
    }

    public void setPackageSize(int packageSize) {
        this.packageSize = packageSize;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        this.total = this.price * this.quantity;
        return this.total;
    }

    public Optional<CreditCard> getCreditCard() {
        return this.creditCard;
    }

    public void setCreditCard(Optional<CreditCard> creditCard) {
        this.creditCard = creditCard;
    }

    public Optional<Address> getBillingAddress() {
        return this.billingAddress;
    }

    public void setBillingAddress(Optional<Address> billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Optional<Address> getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(Optional<Address> shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDateTime getPurchaseDate() {
        return this.purchaseDate;
    }
}