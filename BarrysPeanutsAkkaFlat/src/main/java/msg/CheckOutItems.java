package msg;

import java.util.Vector;

public class CheckOutItems {
    public CheckOutItems(Vector<PurchaseItem> items) {
        this.items = items;

    }

    public Vector<PurchaseItem> getItems() {
        return items;
    }

    public Vector<PurchaseItem> items;
}