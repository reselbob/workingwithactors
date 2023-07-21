package barryspeanuts;

import barryspeanuts.model.PurchaseItem;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.Vector;

@WorkflowInterface
public interface ShoppingCartWorkflow {

    @WorkflowMethod
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
    void CheckOut();

    @SignalMethod
    void Pay();

    @SignalMethod
    void Ship();

    @SignalMethod
    void EmptyCart();
    @SignalMethod
    void exit();
}