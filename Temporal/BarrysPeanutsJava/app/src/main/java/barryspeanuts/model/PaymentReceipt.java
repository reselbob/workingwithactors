package barryspeanuts.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentReceipt {

    public PaymentReceipt(UUID purchaseId, LocalDateTime paymentDate, CreditCard creditCard, UUID transactionId) {
        this.id = UUID.randomUUID();
        this.purchaseId = purchaseId;
        this.paymentDate = paymentDate;
        this.creditCard = creditCard;
        this.transactionId = transactionId;
    }

    public UUID getId() {
        return this.purchaseId;
    }

    public UUID getPurchaseId() {
        return this.purchaseId;
    }

    public void setPurchaseId(UUID purchaseId) {
        this.purchaseId = purchaseId;
    }

    public LocalDateTime getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public CreditCard getCreditCard() {
        return this.creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public UUID getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    UUID id;
    UUID purchaseId;
    LocalDateTime paymentDate;
    CreditCard creditCard;
    UUID transactionId;
}
