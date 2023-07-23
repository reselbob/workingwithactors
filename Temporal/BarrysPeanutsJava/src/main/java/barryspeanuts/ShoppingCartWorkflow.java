package barryspeanuts;
import barryspeanuts.model.Purchase;
import barryspeanuts.model.PurchaseItem;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;
import java.util.Vector;

@WorkflowInterface
public interface ShoppingCartWorkflow {

    @WorkflowMethod
    void holderMethod();

    @SignalMethod
    void startWorkflow();

    // Workflow query method. Used to return our greeting as a query value
    @QueryMethod
    Vector<PurchaseItem> queryPurchaseItems();

    @SignalMethod
    void addItem(PurchaseItem purchaseItem);

    @SignalMethod
    void removeItem(PurchaseItem purchaseItem);

    @SignalMethod
    void clearItems();

    @SignalMethod
    void checkOut(String message);

    @SignalMethod
    void pay();

    @SignalMethod
    void ship();
    @SignalMethod
    void emptyCart();
}