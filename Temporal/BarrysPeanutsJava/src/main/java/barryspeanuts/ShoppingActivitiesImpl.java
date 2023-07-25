package barryspeanuts;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;

import barryspeanuts.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingActivitiesImpl implements ShoppingCartActivities {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingActivitiesImpl.class);

    @Override
    public CheckOutReceipt checkOut(Vector<PurchaseItem> purchaseItems) {
        String str = String.format("%s :  is checking out", ShoppingActivitiesImpl.class);
        logger.info(str);
        Purchase purchase = new Purchase(purchaseItems, new Date());
        return new CheckOutReceipt(purchase, new Date());
    }

    public PaymentReceipt pay(Purchase purchase, CreditCard creditCard) {
        String str = String.format("%s :  is paying", ShoppingActivitiesImpl.class);
        logger.info(str);
        UUID transactionId = UUID.randomUUID();
        return new PaymentReceipt(purchase,new Date(), creditCard,transactionId);
    }

    public ShippingReceipt ship(Purchase purchase, String shipper) {
        String str = String.format("%s :  is shipping to %s", ShoppingActivitiesImpl.class, shipper);
        logger.info(str);
        return new ShippingReceipt(purchase, shipper);
    }
}
