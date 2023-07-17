package msg;

import java.util.Date;
import java.util.Vector;

public class ShippingReceipt {

    public ShippingReceipt(String shipper, Vector<PurchaseItem> purchaseItems, Date shipDate) {
        this.shipper = shipper;
        this.purchaseItems = purchaseItems;
        this.shipDate = shipDate;
    }

    public String getShipper() {
        return shipper;
    }

    public Vector<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public Date getShipDate() {
        return shipDate;
    }

    String shipper;
    Vector<PurchaseItem> purchaseItems;
    Date shipDate;
}
