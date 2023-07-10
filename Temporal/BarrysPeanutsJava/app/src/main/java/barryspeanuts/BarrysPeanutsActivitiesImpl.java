package barryspeanuts;

import java.time.LocalDateTime;
import java.util.UUID;

import barryspeanuts.model.PaymentReceipt;
import barryspeanuts.model.Purchase;

public class BarrysPeanutsActivitiesImpl implements BarrysPeanutsActivities{

    public Purchase checkOut(Purchase purchase){
        purchase.purchaseDate = LocalDateTime.now();
        return purchase;
    }

     public PaymentReceipt pay(Purchase purchase){
        PaymentReceipt  receipt = new PaymentReceipt(purchase.getId(), purchase.getPurchaseDate(), null, UUID.randomUUID())

        return receipt;
    }


}
