package com.miun.martinclass.demo.menu.service;
import com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem;
import com.miun.martinclass.demo.menu.entity.MenuItem;
import com.miun.martinclass.demo.menu.entity.DailyMenu;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MenuService {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    // ========== MenuItem CRUD ==========

    /**
     * Get all menu items in the catalog
     */
    public List<MenuItem> getAllMenuItems() {
        return em.createQuery("SELECT m FROM MenuItem m", MenuItem.class).getResultList();
    }

    /**
     * Find a specific menu item by ID
     */
    public MenuItem findMenuItemById(Long id) {
        return em.find(MenuItem.class, id);
    }

    /**
     * Create a new menu item
     */
    public void createMenuItem(MenuItem item) {
        em.persist(item);
    }

    /**
     * Update an existing menu item
     */
    public void updateMenuItem(MenuItem item) {
        em.merge(item);
    }

    /**
     * Delete a menu item
     */
    public void deleteMenuItem(Long id) {
        MenuItem item = em.find(MenuItem.class, id);
        if(item != null) {
            em.remove(item);
        }
    }

    /**
     * Find menu items by dietary restrictions
     */
    public List<MenuItem> findVeganItems() {
        return em.createQuery("Select m from MenuItem m WHERE m.isVegan", MenuItem.class).getResultList();
    }

    public List<MenuItem> findGlutenFreeItems() {
        return em.createQuery("SELECT m FROM MenuItem m WHERE m.isGlutenFree = true", MenuItem.class).getResultList();
    }

    // ========== WeeklyMenu CRUD ==========

    /**
     * Get all weekly menus
     */
    public List<DailyMenu> getAllDailyMenus() {
        return em.createQuery("SELECT d FROM DailyMenu d ORDER BY d.date DESC", DailyMenu.class).getResultList();
    }

    /**
     * Find a specific daily menu by ID
     */
    public DailyMenu findDailyMenuById(Long id) {
        return em.find(DailyMenu.class, id);
    }

    /**
     * Create a new daily menu
     */
    public void createDailyMenu(DailyMenu menu) {
        em.persist(menu);
    }

    /**
     * Update an existing daily menu
     */
    public void updateDailyMenu(DailyMenu menu) {
        em.merge(menu);
    }

    /**
     * Delete a daily menu
     */
    public void deleteDailyMenu(Long id) {
        DailyMenu item = em.find(DailyMenu.class, id);
        if(item != null) {
            em.remove(item);
        }
    }

    // ========== Key Query Methods ==========

    /**
     * Get the current active daily menu (most important!)
     */
    public DailyMenu getTodaysMenu() {
        List<DailyMenu> results = em.createQuery("SELECT d FROM DailyMenu d WHERE d.date = :date", DailyMenu.class)
                .setParameter("date", LocalDate.now())
                .getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    /**
     * Get daily menu for a specific date
     */
    public DailyMenu getDailyMenuForDate(LocalDate date) {
        List<DailyMenu> result = em.createQuery(
                "SELECT d FROM DailyMenu d WHERE d.date = :date", DailyMenu.class)
                .setParameter("date", date)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Add a menu item to a daily menu
     */
    public void addItemToDailyMenu(Long dailyMenuId, Long menuItemId) {
        DailyMenu dailyMenu = em.find(DailyMenu.class, dailyMenuId);
        MenuItem menuItem = em.find(MenuItem.class, menuItemId);

        if(dailyMenu != null && menuItem != null) {
            dailyMenu.getMenuItems().add(menuItem);
            em.merge(dailyMenu);
        }
    }

    /**
     * Remove a menu item from a daily menu
     */
    public void removeItemFromDailyMenu(Long dailyMenuId, Long menuItemId) {
        DailyMenu dailyMenu = em.find(DailyMenu.class, dailyMenuId);
        MenuItem menuItem = em.find(MenuItem.class, menuItemId);
        System.out.println(dailyMenuId);
        System.out.println(menuItemId);
        System.out.println(dailyMenu);
        System.out.println(menuItem);
        System.out.println("hello");
        if (dailyMenu != null && menuItem != null) {
            System.out.println("WE ARE HERE ERIK WE GOT TO THIS PART");
            dailyMenu.getMenuItems().remove(menuItem);
            em.merge(dailyMenu);
            em.flush();  // Force the SQL to execute now
        }
        System.out.println("IT DID NOT WORK");
    }

    public List<CarteMenuItem> getActiveCarteMenuItems() {
        return em.createQuery("""
    SELECT new com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem(
        m.id, m.name, m.description, m.price, m.isVegan, m.isGlutenFree, m.allergens,
        c.id, c.activeTime, c.waitingTime, c.isMeat,
        c.isDessert, c.canSubstitute, c.isAppetizer, c.isHuvud
    )
    FROM CarteMenu cm
    JOIN cm.menuItems m
    LEFT JOIN m.carteAtributes c
    WHERE cm.active = TRUE
""", CarteMenuItem.class).getResultList();
    }
}