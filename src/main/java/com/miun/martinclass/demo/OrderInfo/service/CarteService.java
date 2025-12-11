package com.miun.martinclass.demo.OrderInfo.service;
import com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CarteService {
    private EntityManager em;
// Add these methods to your MenuService class
    /**
     * Get all menu items with their carte attributes in one query
     */
    public List<CarteMenuItem> getAllCarteMenuItems() {
        return em.createQuery("""
                    SELECT new com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem(
                        m.id, m.name, m.description, m.price, m.isVegan, m.isGlutenFree, m.allergens,
                        c.id, c.activeTime, c.waitingTime,  c.isMeat, 
                        c.isDessert, c.canSubstitute, c.isAppetizer, c.isHuvud
                    )
                    FROM MenuItem m
                    LEFT JOIN CarteAtributes c ON c.menuItem.id = m.id
                """, CarteMenuItem.class).getResultList();
    }

    /**
     * Find a specific carte menu item by ID
     */
    public CarteMenuItem findCarteMenuItemById(Long id) {
        List<CarteMenuItem> results = em.createQuery("""
                            SELECT new com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem(
                                m.id, m.name, m.description, m.price, m.isVegan, m.isGlutenFree, m.allergens,
                                c.id, c.activeTime, c.waitingTime, c.isMeat, 
                                c.isDessert, c.canSubstitute, c.isAppetizer, c.isHuvud
                            )
                            FROM MenuItem m
                            LEFT JOIN CarteAtributes c ON c.menuItem.id = m.id
                            WHERE m.id = :id
                        """, CarteMenuItem.class)
                .setParameter("id", id)
                .getResultList();

        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * Find all appetizers with their attributes
     */
    public List<CarteMenuItem> findAllAppetizers() {
        return em.createQuery("""
                    SELECT new com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem(
                        m.id, m.name, m.description, m.price, m.isVegan, m.isGlutenFree, m.allergens,
                        c.id, c.activeTime, c.waitingTime, c.isMeat,
                        c.isDessert, c.canSubstitute, c.isAppetizer, c.isHuvud
                    )
                    FROM MenuItem m
                    LEFT JOIN CarteAtributes c ON c.menuItem.id = m.id
                    WHERE c.isAppetizer = true
                """, CarteMenuItem.class).getResultList();
    }

    /**
     * Find all main courses with their attributes
     */
    public List<CarteMenuItem> findAllMainCourses() {
        return em.createQuery("""
                    SELECT new com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem(
                        m.id, m.name, m.description, m.price, m.isVegan, m.isGlutenFree, m.allergens,
                        c.id, c.activeTime, c.waitingTime, c.isMeat,
                        c.isDessert, c.canSubstitute, c.isAppetizer, c.isHuvud
                    )
                    FROM MenuItem m
                    LEFT JOIN CarteAtributes c ON c.menuItem.id = m.id
                    WHERE c.isHuvud = true
                """, CarteMenuItem.class).getResultList();
    }

    /**
     * Find all desserts with their attributes
     */
    public List<CarteMenuItem> findAllDesserts() {
        return em.createQuery("""
                    SELECT new com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem(
                        m.id, m.name, m.description, m.price, m.isVegan, m.isGlutenFree, m.allergens,
                        c.id, c.activeTime, c.waitingTime, c.isMeat,
                        c.isDessert, c.canSubstitute, c.isAppetizer, c.isHuvud
                    )
                    FROM MenuItem m
                    LEFT JOIN CarteAtributes c ON c.menuItem.id = m.id
                    WHERE c.isDessert = true
                """, CarteMenuItem.class).getResultList();
    }
}