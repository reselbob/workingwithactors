package actor;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import msg.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;

public class CheckOutActor extends AbstractBehavior<Object> {
    Logger logger = LoggerFactory.getLogger(ShoppingCartActor.class);

    private CheckOutActor(ActorContext<Object> context) {
        super(context);
    }

    public static Behavior<Object> create() {
        return Behaviors.setup(CheckOutActor::new);
    }

    public static Behavior<Object> behavior() {
        return Behaviors.setup(CheckOutActor::new);
    }

    @Override
    public Receive<Object> createReceive() {
        return newReceiveBuilder()
                .onMessage(StartCheckout.class, this::handleStartCheckout)
                .build();
    }

    private Behavior<Object> handleStartCheckout(StartCheckout msg) {
        /* get the credit card number from the customer
        CustomerActor.CreditCardRequest creditCardRequest = new CustomerActor.CreditCardRequest(msg.getPurchaseItems());
        ActorSystem<Object> customerActor = ActorSystem.create(CustomerActor.create(), "customerActor");
        customerActor.ask(creditCardRequest);*/
        PaymentActor.PaymentInfo paymentInfo = new PaymentActor.PaymentInfo(msg.getCustomer(),
                msg.getCreditCard(),
                msg.getPurchaseItems());
        ActorSystem<Object> paymentActor = ActorSystem.create(PaymentActor.create(), "paymentActor");
        paymentActor.tell(paymentInfo);
        return this;
    }





    public static class StartCheckout {
        public StartCheckout(ArrayList<PurchaseItem> purchaseItems, CreditCard creditCard, Customer customer) {
            this.purchaseItems = purchaseItems;
            this.creditCard = creditCard;
            this.customer = customer;
        }

        public ArrayList<PurchaseItem> getPurchaseItems() {
            return purchaseItems;
        }

        public CreditCard getCreditCard() {
            return creditCard;
        }

        public Customer getCustomer() {
            return customer;
        }

        ArrayList<PurchaseItem> purchaseItems;

        CreditCard creditCard;

        Customer customer;

    }





}
