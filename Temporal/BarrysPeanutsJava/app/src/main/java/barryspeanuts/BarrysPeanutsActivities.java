package barryspeanuts;

import io.temporal.activity.ActivityInterface;
import barryspeanuts.model.PaymentReceipt;
import barryspeanuts.model.Purchase;
import barryspeanuts.model.ShippingReceipt;

@ActivityInterface
public interface BarrysPeanutsActivities {

    // Define your activity methods which can be called during workflow execution
    Purchase checkOut(Purchase purcahse);
    PaymentReceipt pay(Purchase purchase);
    ShippingReceipt ship(Purchase purchase, String shipper);
    
}