package com.miun.martinclass.demo.OrderInfo.service;
import com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem;
import com.miun.martinclass.demo.OrderInfo.entity.OrderGroup;
import com.miun.martinclass.demo.OrderInfo.entity.SimpleOrder;
import com.miun.martinclass.demo.menu.entity.CarteMenu;
import com.miun.martinclass.demo.menu.entity.MenuItem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CarteService {

    @PersistenceContext(unitName = "default")
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
                    LEFT JOIN m.carteAtributes c
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
                            LEFT JOIN m.carteAtributes c
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
                    LEFT JOIN m.carteAtributes c
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
                    LEFT JOIN m.carteAtributes c
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
                    LEFT JOIN m.carteAtributes c
                    WHERE c.isDessert = true
                """, CarteMenuItem.class).getResultList();
    }

    // ==== New methods for Admin ====
    public List<CarteMenu> getAllCarteMenus() {
        return em.createQuery("SELECT c FROM CarteMenu c", CarteMenu.class)
                .getResultList();
    }

    public void createCarteMenu(CarteMenu menu) {
        em.persist(menu);
    }

    public void deleteCarteMenu(Long id) {
        CarteMenu menu = em.find(CarteMenu.class, id);
        if (menu != null) em.remove(menu);
    }

    public void removeItemFromCarteMenu(Long carteMenuId, Long menuItemId) {
        CarteMenu carteMenu = em.find(CarteMenu.class, carteMenuId);
        MenuItem menuItem = em.find(MenuItem.class, menuItemId);
        if (carteMenu != null && menuItem != null) {
            carteMenu.getMenuItems().remove(menuItem);
            em.merge(carteMenu);
        }
    }

    public void addItemToCarteMenu(Long carteMenuId, Long menuItemId) {
        CarteMenu carteMenu = em.find(CarteMenu.class, carteMenuId);
        MenuItem menuItem = em.find(MenuItem.class, menuItemId);
        if (carteMenu != null && menuItem != null) {
            carteMenu.getMenuItems().add(menuItem);
            em.merge(carteMenu);
        }
    }



}