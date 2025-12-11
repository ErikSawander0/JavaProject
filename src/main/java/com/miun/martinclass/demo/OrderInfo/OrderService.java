package com.miun.martinclass.demo.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<OrderGroup> allOrders;

    public OrderService() {
        this.allOrders = new ArrayList<>();
    }

    public void saveOrder(OrderGroup orderGroup) {
        allOrders.add(orderGroup);

    }

    public List<OrderGroup> getOrders() {

        return allOrders;
    }
}
