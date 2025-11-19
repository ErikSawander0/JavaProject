package com.miun.martinclass.demo.menu.service;
import com.miun.martinclass.demo.menu.entity.MenuItem;
import com.miun.martinclass.demo.menu.entity.DailyMenu;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MenuService {

    //@PersistenceContext(unitName = "menuPU")
    private EntityManager em;

    // ========== MenuItem CRUD ==========

    /**
     * Get all menu items in the catalog
     */
    public List<MenuItem> getAllMenuItems() {
        return null;
    }

    /**
     * Find a specific menu item by ID
     */
    public MenuItem findMenuItemById(Long id) {
        return null;
    }

    /**
     * Create a new menu item
     */
    public void createMenuItem(MenuItem item) {

    }

    /**
     * Update an existing menu item
     */
    public void updateMenuItem(MenuItem item) {

    }

    /**
     * Delete a menu item
     */
    public void deleteMenuItem(Long id) {

    }

    /**
     * Find menu items by dietary restrictions
     */
    public List<MenuItem> findVeganItems() {
        return null;
    }

    public List<MenuItem> findGlutenFreeItems() {
        return null;
    }

    // ========== WeeklyMenu CRUD ==========

    /**
     * Get all weekly menus
     */
    public List<DailyMenu> getAllDailyMenus() {
        return null;
    }

    /**
     * Find a specific daily menu by ID
     */
    public DailyMenu findDailyMenuById(Long id) {
        return null;
    }

    /**
     * Create a new daily menu
     */
    public void createDailyMenu(DailyMenu menu) {

    }

    /**
     * Update an existing daily menu
     */
    public void updateDailyMenu(DailyMenu menu) {

    }

    /**
     * Delete a daily menu
     */
    public void deleteDailyMenu(Long id) {

    }

    // ========== Key Query Methods ==========

    /**
     * Get the current active daily menu (most important!)
     */
    public DailyMenu getTodaysMenu() {
        // Create a dummy DailyMenu for testing
        DailyMenu menu = new DailyMenu();
        menu.setId(1L);
        menu.setDate(LocalDate.now());

        // Create some dummy MenuItems
        List<MenuItem> items = new ArrayList<>();

        MenuItem item1 = new MenuItem();
        item1.setId(1L);
        item1.setName("Pasta Alfredo");
        item1.setDescription("Creamy pasta with parmesan cheese and garlic");
        item1.setPrice(new BigDecimal("12.99"));
        item1.setIsVegan(false);
        item1.setIsGlutenFree(false);
        item1.setAllergens("dairy, gluten");
        items.add(item1);

        MenuItem item2 = new MenuItem();
        item2.setId(2L);
        item2.setName("Veggie Burger");
        item2.setDescription("Plant-based burger with lettuce, tomato, and fries");
        item2.setPrice(new BigDecimal("9.50"));
        item2.setIsVegan(true);
        item2.setIsGlutenFree(false);
        item2.setAllergens("gluten");
        items.add(item2);

        MenuItem item3 = new MenuItem();
        item3.setId(3L);
        item3.setName("Grilled Salmon");
        item3.setDescription("Fresh Atlantic salmon with roasted vegetables");
        item3.setPrice(new BigDecimal("18.99"));
        item3.setIsVegan(false);
        item3.setIsGlutenFree(true);
        item3.setAllergens("fish");
        items.add(item3);

        MenuItem item4 = new MenuItem();
        item4.setId(4L);
        item4.setName("Caesar Salad");
        item4.setDescription("Romaine lettuce with Caesar dressing and croutons");
        item4.setPrice(new BigDecimal("8.50"));
        item4.setIsVegan(false);
        item4.setIsGlutenFree(false);
        item4.setAllergens("dairy, gluten, eggs");
        items.add(item4);

        // Set the items in the menu
        menu.setMenuItems(items);

        return menu;
    }

    /**
     * Get daily menu for a specific date
     */
    public DailyMenu getDailyMenuForDate(LocalDate date) {
        return null;
    }

    /**
     * Add a menu item to a daily menu
     */
    public void addItemToDailyMenu(Long weeklyMenuId, Long menuItemId) {

    }

    /**
     * Remove a menu item from a daily menu
     */
    public void removeItemFromDailyMenu(Long weeklyMenuId, Long menuItemId) {

    }
}