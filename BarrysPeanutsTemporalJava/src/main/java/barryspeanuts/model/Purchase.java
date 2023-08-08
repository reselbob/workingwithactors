package barryspeanuts.model;

import java.util.Date;
import java.util.List;
public class Purchase {

    public Purchase(){}
    public Purchase(List<PurchaseItem> purchaseItems, Date purchaseDate) {
        this.purchaseItems = purchaseItems;
        this.purchaseDate = purchaseDate;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }


    public Date getPurchaseDate() {
        return purchaseDate;
    }

    String id;
    List<PurchaseItem> purchaseItems;
    Date purchaseDate;
}
