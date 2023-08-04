package barryspeanuts.msg;

import java.util.ArrayList;
import java.util.Vector;

public class CheckOutItems {
    public CheckOutItems(ArrayList<PurchaseItem> items) {
        this.items = items;

    }

    public ArrayList<PurchaseItem> getItems() {
        return items;
    }

    public ArrayList<PurchaseItem> items;
}