package barryspeanuts.model;

import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class Purchase {

    public Purchase(){}
    public Purchase(UUID id, Vector<PurchaseItem> purchaseItems, Date purchaseDate) {
        this.id = id;
        this.purchaseItems = purchaseItems;
        this.purchaseDate = purchaseDate;
    }

    public UUID getId() {
        return id;
    }

    public Vector<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }


    public Date getPurchaseDate() {
        return purchaseDate;
    }

    UUID id;
    Vector<PurchaseItem> purchaseItems;
    Date purchaseDate;
}
