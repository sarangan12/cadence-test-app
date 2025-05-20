package org.example;

import com.uber.cadence.workflow.Async;
import com.uber.cadence.workflow.Promise;
import com.uber.cadence.workflow.Workflow;

import java.time.Duration;
import java.util.UUID;

public class EatsOrderActivitiesImpl implements EatsOrderActivities{
    private UUID userId;
    private Order order;
    private UUID restuarantId;

    @Override
    public String receiveOrder(UUID userId, Order order, UUID restuarantId) {
        this.userId = userId;
        this.order = order;
        this.restuarantId = restuarantId;

        return String.format("Received order from user %s for restaurant %s: Order ID %s with %s",
                this.userId, this.restuarantId, this.order.getOrderId(), String.join(", ", this.order.getOrderItem()));
    }

    @Override
    public String prepareOrder() {
        Workflow.sleep(Duration.ofSeconds(3));
        return String.format("Order Id: %s  Prepared", order.getOrderId());
    }

    @Override
    public String callDeliveryWorkflow(UUID orderId) {
        DeliverOrder child = Workflow.newChildWorkflowStub(DeliverOrder.class);
        Promise<String> childPromise = Async.function(child::deliverOrder, orderId);
        return childPromise.get();
    }

    @Override
    public String printFinalMessage(UUID orderId) {
        return String.format("Your Order with Order Id: %s is in front of your door!!!", orderId);
    }
}