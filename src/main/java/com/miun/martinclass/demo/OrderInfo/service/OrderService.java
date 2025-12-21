package com.miun.martinclass.demo.OrderInfo.service;

import com.miun.martinclass.demo.OrderInfo.entity.OrderGroup;
import com.miun.martinclass.demo.OrderInfo.entity.SimpleOrder;
import com.miun.martinclass.demo.menu.entity.CarteAtributes;
import com.miun.martinclass.demo.menu.entity.MenuItem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrderService {

    @PersistenceContext(unitName = "default")
    private EntityManager em;
    public  long saveOrder(OrderGroup orderGroup) {
        em.persist(orderGroup);
        em.flush();
        return orderGroup.getID();
    }
    public List<OrderGroup> getOrders() {
        return em.createQuery("SELECT m FROM OrderGroup m",  OrderGroup.class).getResultList();
    }
    public List<OrderGroup> getActiveOrders() {
        return em.createQuery(
                "SELECT og FROM OrderGroup og WHERE og.isDone = false",
                OrderGroup.class
        ).getResultList();
    }
    public void markOrderasDone(Long id) {
        OrderGroup og = em.find(OrderGroup.class, id);
        if (og != null) {
            og.setIsDone(true);
        }
    }
    public boolean isOrderDone(Long orderGroupId) {
        OrderGroup og = em.find(OrderGroup.class, orderGroupId);
        if (og != null) {
            return og.getIsDone();
        } else {
            throw new IllegalArgumentException("OrderGroup with ID " + orderGroupId + " not found");
        }
    }

    public List<Long> findActiveGroupIds() {
        return em.createQuery(
                "SELECT DISTINCT o.id FROM OrderGroup o WHERE o.isDone = false",
                Long.class
        ).getResultList();
    }

    public void setCookTimes(OrderGroup orderGroup) {
        for (SimpleOrder order : orderGroup.getOrders()) {
            // Get the original menu item ID
            Long menuItemId = order.getOriginalID();

            // Query the database for carte attributes
            String query = "SELECT ca FROM CarteAtributes ca WHERE ca.menuItem.id = :menuItemId";
            CarteAtributes attributes = em.createQuery(query, CarteAtributes.class)
                    .setParameter("menuItemId", menuItemId)
                    .getSingleResult();

            // Set the times on the order
            order.setActiveTime(attributes.getActiveTime());
            order.setWaitingTime(attributes.getWaitingTime());
        }
    }
}
