package barryspeanuts.model;

import java.util.Date;
import java.util.UUID;

public class ShippingReceipt {

    public ShippingReceipt() {
    }

    public ShippingReceipt(Purchase purchase, String shipper) {
        this.id = UUID.randomUUID();
        this.shipDate = new Date();
        this.purchaseId = purchase.getId();
        this.shipper = shipper;
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getPurchaseId() {
        return purchaseId;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public String getShipper() {
        return shipper;
    }

    UUID id;
    UUID purchaseId;
    Date shipDate;
    String shipper;
}
