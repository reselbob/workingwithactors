package barryspeanuts.model;

import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class Purchase {

    public Purchase(){}
    public Purchase(Vector<PurchaseItem> purchaseItems, Date purchaseDate) {
        this.purchaseItems = purchaseItems;
        this.purchaseDate = purchaseDate;
    }

    public Vector<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }


    public Date getPurchaseDate() {
        return purchaseDate;
    }

    String id;
    Vector<PurchaseItem> purchaseItems;
    Date purchaseDate;
}
