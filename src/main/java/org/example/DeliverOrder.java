package org.example;

import com.uber.cadence.workflow.WorkflowMethod;

import java.util.UUID;

public interface DeliverOrder {
    @WorkflowMethod
    String deliverOrder(UUID orderId);
}
