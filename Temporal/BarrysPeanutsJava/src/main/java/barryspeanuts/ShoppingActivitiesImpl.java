package barryspeanuts;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;

import barryspeanuts.model.*;

public class ShoppingActivitiesImpl implements ShoppingCartActivities {

    @Override
    public CheckOutReceipt checkOut(Vector<PurchaseItem> purchaseItems) {
        Purchase purchase = new Purchase(purchaseItems, new Date());
        return new CheckOutReceipt(purchase, new Date());
    }

    public PaymentReceipt pay(Purchase purchase, CreditCard creditCard) {
        UUID transactionId = UUID.randomUUID();
        return new PaymentReceipt(purchase,new Date(), creditCard,transactionId);
    }

    public ShippingReceipt ship(Purchase purchase, String shipper) {
        return new ShippingReceipt(purchase, shipper);
    }


}