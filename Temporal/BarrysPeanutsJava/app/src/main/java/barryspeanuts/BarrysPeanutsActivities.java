package barryspeanuts;

import io.temporal.activity.ActivityInterface;
import barryspeanuts.model.PaymentReceipt;
import barryspeanuts.model.Purchase;
import barryspeanuts.model.ShippingReceipt;
import barryspeanuts.model.CreditCard;

@ActivityInterface
public interface BarrysPeanutsActivities {
    Purchase checkOut(Purchase purchase);
    PaymentReceipt pay(Purchase purchase, CreditCard creditCard);
    ShippingReceipt ship(Purchase purchase, String shipper);
    
}