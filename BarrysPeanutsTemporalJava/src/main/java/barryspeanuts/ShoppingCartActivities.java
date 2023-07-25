package barryspeanuts;

import barryspeanuts.model.*;
import io.temporal.activity.ActivityInterface;

import java.util.Vector;

@ActivityInterface
public interface ShoppingCartActivities {
    CheckOutReceipt checkOut(Vector<PurchaseItem> purchaseItems);
    PaymentReceipt pay(Purchase purchase, CreditCard creditCard);
    ShippingReceipt ship(Purchase purchase, String shipper);
}