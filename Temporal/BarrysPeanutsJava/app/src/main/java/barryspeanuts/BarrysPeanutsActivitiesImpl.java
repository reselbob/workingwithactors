package barryspeanuts;

import java.time.LocalDateTime;
import java.util.UUID;

import barryspeanuts.model.ShippingReceipt;
import barryspeanuts.model.PaymentReceipt;
import barryspeanuts.model.Purchase;
import barryspeanuts.model.CreditCard;

public class BarrysPeanutsActivitiesImpl implements BarrysPeanutsActivities{

    public Purchase checkOut(Purchase purchase){
        purchase.purchaseDate = LocalDateTime.now();
        return purchase;
    }

     public PaymentReceipt pay(Purchase purchase, CreditCard creditCard){
        PaymentReceipt  receipt = new PaymentReceipt(purchase.getId(), purchase.getPurchaseDate(), creditCard, UUID.randomUUID());

        return receipt;
    }

    public ShippingReceipt ship(Purchase purchase, String shipper){
        ShippingReceipt receipt = new ShippingReceipt(purchase.id, shipper);
        return receipt;

    }
}
