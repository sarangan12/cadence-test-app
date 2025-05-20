package org.example;

import java.util.UUID;

public class DeliverOrderImpl implements DeliverOrder{
    @Override
    public String deliverOrder(UUID orderId) {
        return "Order Id: " + orderId.toString() + " delivered!!!";
    }
}
