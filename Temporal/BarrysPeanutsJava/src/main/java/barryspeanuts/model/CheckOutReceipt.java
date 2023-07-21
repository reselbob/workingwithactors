package barryspeanuts.model;

import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class CheckOutReceipt {
    UUID id;
    Date confirmationDate;

    public CheckOutReceipt(){};

    public CheckOutReceipt(Date confirmationDate, Vector<PurchaseItem> purchaseItems) {
        this.id = UUID.randomUUID();
        this.confirmationDate = confirmationDate;
        this.purchaseItems = purchaseItems;
    }

    public UUID getId() {
        return id;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public Vector<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    Vector<PurchaseItem> purchaseItems;
}
