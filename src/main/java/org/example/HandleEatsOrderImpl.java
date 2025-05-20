package org.example;

import com.uber.cadence.workflow.Workflow;
import com.uber.cadence.activity.ActivityOptions;
import java.time.Duration;
import java.util.UUID;

public class HandleEatsOrderImpl implements HandleEatsOrder {
    private int waiterSignal = -1;

    @Override
    public void waitForSignal(int signal) {
        waiterSignal = signal;
    }

    @Override
    public String startOrder(UUID userId, Order order, UUID restuarantId) {
        EatsOrderActivities activities =
                Workflow.newActivityStub(
                        EatsOrderActivities.class,
                        new ActivityOptions.Builder()
                                .setScheduleToCloseTimeout(Duration.ofSeconds(10)).
                                build());

        String receiveMsg = activities.receiveOrder(userId, order, restuarantId);
        Workflow.await(() -> waiterSignal == 0 || waiterSignal == 1);
        if (waiterSignal == 0) {
            return "Order rejected by waiter.";
        }

        String prepMsg = activities.prepareOrder();
        String deliveryMsg = activities.callDeliveryWorkflow(order.getOrderId());
        String finalMsg = activities.printFinalMessage(order.getOrderId());
        return receiveMsg + "\n" + prepMsg + "\n" + deliveryMsg + "\n" + finalMsg;
    }
}
