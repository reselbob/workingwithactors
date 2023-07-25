package barryspeanuts.model;

import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class CheckOutReceipt {
    UUID id;
    Date confirmationDate;

    public CheckOutReceipt(){};

    public CheckOutReceipt(Purchase purchase, Date confirmationDate) {
        this.id = UUID.randomUUID();
        this.confirmationDate = confirmationDate;
        this.purchase = purchase;
    }

    public UUID getId() {
        return id;
    }

    public Date getConfirmationDate() {
        return this.confirmationDate;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }

    Purchase purchase;
}
