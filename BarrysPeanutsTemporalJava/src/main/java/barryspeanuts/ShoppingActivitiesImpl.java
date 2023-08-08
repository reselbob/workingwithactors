package barryspeanuts;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import barryspeanuts.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingActivitiesImpl implements ShoppingCartActivities {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingActivitiesImpl.class);

    @Override
    public CheckOutReceipt checkOut(List<PurchaseItem> purchaseItems) {
        logger.info("{} is checking out", ShoppingActivitiesImpl.class);
        Purchase purchase = new Purchase(purchaseItems, new Date());
        return new CheckOutReceipt(purchase, new Date());
    }

    public PaymentReceipt pay(Purchase purchase, CreditCard creditCard) {
        logger.info("{} is paying", ShoppingActivitiesImpl.class);
        UUID transactionId = UUID.randomUUID();
        return new PaymentReceipt(purchase,new Date(), creditCard,transactionId);
    }

    public ShippingReceipt ship(Purchase purchase, String shipper) {
        logger.info("{} is shipping to {}", ShoppingActivitiesImpl.class, shipper);
        return new ShippingReceipt(purchase, shipper);
    }
}
