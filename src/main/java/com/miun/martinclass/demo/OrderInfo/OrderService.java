package com.miun.martinclass.demo.OrderInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    //private List<OrderGroup> allOrders;
    private EntityManager entityManager;


    public OrderService() {}

    @Transactional
    public void saveOrder(OrderGroup orderGroup) {
        entityManager.persist(orderGroup);

    }


    public List<OrderGroup> getOrders() {

        return entityManager.createQuery("SELECT o FROM OrderGroup o").getResultList();
    }
}
