package org.example;

import java.util.UUID;

public interface EatsOrderActivities {
    String receiveOrder(UUID userId, Order order, UUID restuarantId);
    String prepareOrder();
    String callDeliveryWorkflow(UUID orderId);
    String printFinalMessage(UUID orderId);
}
