package org.example;

import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;

import java.util.UUID;

public interface HandleEatsOrder {
    @WorkflowMethod
    public String startOrder(UUID userId, Order order, UUID restuarantId);

    @SignalMethod
    public void waitForSignal(int signal);
}
