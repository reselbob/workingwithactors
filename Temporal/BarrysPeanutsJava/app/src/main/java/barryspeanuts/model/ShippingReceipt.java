package barryspeanuts.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShippingReceipt {

    public ShippingReceipt(){}

    public ShippingReceipt(UUID purchaseId, String shipper) {
        this.id = UUID.randomUUID();
        this.shipDate = LocalDateTime.now();
        this.purchaseId = purchaseId;
        this.shipper = shipper;
    }

     public UUID getId() {
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
        return this.id;
    }
    public UUID getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(UUID purchaseId) {
        this.purchaseId = purchaseId;
    }
    public LocalDateTime getShipDate() {
        return shipDate;
    }
    public void setShipDate(LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }
    public String getShipper() {
        return shipper;
    }
    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    UUID id;
    UUID purchaseId;
    LocalDateTime shipDate;
    String shipper;
}
