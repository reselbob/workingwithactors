package msg;

public class ShipItem {
    String shipper;

    public String getShipper() {
        return shipper;
    }

    public CheckOutItems getCheckOutItems() {
        return checkOutItems;
    }

    public ShipItem(String shipper, CheckOutItems checkOutItems) {
        this.shipper = shipper;
        this.checkOutItems = checkOutItems;
    }

    CheckOutItems checkOutItems;
}
