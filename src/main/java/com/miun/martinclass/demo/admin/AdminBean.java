package com.miun.martinclass.demo.admin;

import com.miun.martinclass.demo.OrderInfo.service.CarteService;
import com.miun.martinclass.demo.menu.entity.CarteMenu;
import com.miun.martinclass.demo.menu.entity.DailyMenu;
import com.miun.martinclass.demo.menu.entity.MenuItem;
import com.miun.martinclass.demo.menu.service.MenuService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

//Remember to use the correct @ over whatever class or function who wants it.
public class AdminBean implements Serializable {

    private MenuService menuService;
    private CarteService carteService;

    private List<MenuItem> menuItems;
    private List<DailyMenu> dailyMenus;
    private List<CarteMenu> carteMenus;

    private MenuItem selectedMenuItem;
    private DailyMenu selectedDailyMenu;
    private CarteMenu selectedCarteMenu;

    private MenuItem newMenuItem = new MenuItem();
    private DailyMenu newDailyMenu = new DailyMenu();
    private CarteMenu newCarteMenu = new CarteMenu();

    private Long selectedMenuItemId;
    private Long selectedDailyMenuId;
    private Long selectedCarteMenuId;

    /* TODO: Create a function to initialize the bean:
    public void initFunction() {
        // call to reloadData()
    } */

    /* TODO: Create a function to reload all collections:
    public void reloadDataFunction() {
        // logic to fetch menuItems, dailyMenus, carteMenus
    } */

    /* TODO: Create a function to add a new MenuItem:
    public void addMenuItemFunction() {
        // logic to save newMenuItem
    } */

    /* TODO: Create a function to update a selected MenuItem:
    public void updateMenuItemFunction() {
        // logic to update selectedMenuItem
    } */

    /* TODO: Create a function to remove a MenuItem:
    public void removeMenuItemFunction(MenuItem item) {
        // logic to delete item by id
    } */

    /* TODO: Create a function to add a new DailyMenu:
    public void addDailyMenuFunction() {
        // logic to save newDailyMenu
    } */

    /* TODO: Create a function to remove a DailyMenu:
    public void removeDailyMenuFunction(DailyMenu menu) {
        // logic to delete menu by id
    } */

    /* TODO: Create a function to add a MenuItem to a DailyMenu:
    public void addMenuItemToDailyMenuFunction() {
        // logic to add selectedMenuItemId to selectedDailyMenuId
    } */

    /* TODO: Create a function to remove a MenuItem from a DailyMenu:
    public void removeMenuItemFromDailyMenuFunction(DailyMenu dailyMenu, MenuItem menuItem) {
        // logic to remove menuItem from dailyMenu
    } */

    /* TODO: Create a function to add a new CarteMenu:
    public void addCarteMenuFunction() {
        // logic to save newCarteMenu
    } */

    /* TODO: Create a function to remove a CarteMenu:
    public void removeCarteMenuFunction(CarteMenu menu) {
        // logic to delete menu by id
    } */

    /* TODO: Create a function to add a MenuItem to a CarteMenu:
    public void addMenuItemToCarteMenuFunction() {
        // logic to add selectedMenuItemId to selectedCarteMenuId
    } */

    /* TODO: Create a function to remove a MenuItem from a CarteMenu:
    public void removeMenuItemFromCarteMenuFunction(CarteMenu menu, MenuItem item) {
        // logic to remove item from menu
    } */

    /* TODO: Create a function to update attributes of a MenuItem:
    public void updateCarteAttributesFunction(MenuItem item) {
        // logic to update item
    } */

    /* TODO: Create a function to fetch all MenuItems:
    public List<MenuItem> getMenuItemsFunction() {
        // return menuItems
    } */

    /* TODO: Create a function to fetch all DailyMenus:
    public List<DailyMenu> getDailyMenusFunction() {
        // return dailyMenus
    } */

    /* TODO: Create a function to fetch all CarteMenus:
    public List<CarteMenu> getCarteMenusFunction() {
        // return carteMenus
    } */

    /* TODO: Create getters/setters for newMenuItem:
    public MenuItem getNewMenuItemFunction() { return newMenuItem; }
    public void setNewMenuItemFunction(MenuItem newMenuItem) { this.newMenuItem = newMenuItem; }
    */

    /* TODO: Create getters/setters for newDailyMenu:
    public DailyMenu getNewDailyMenuFunction() { return newDailyMenu; }
    public void setNewDailyMenuFunction(DailyMenu newDailyMenu) { this.newDailyMenu = newDailyMenu; }
    */

    /* TODO: Create getters/setters for newCarteMenu:
    public CarteMenu getNewCarteMenuFunction() { return newCarteMenu; }
    public void setNewCarteMenuFunction(CarteMenu newCarteMenu) { this.newCarteMenu = newCarteMenu; }
    */

    /* TODO: Create getters/setters for selectedMenuItemId:
    public Long getSelectedMenuItemIdFunction() { return selectedMenuItemId; }
    public void setSelectedMenuItemIdFunction(Long selectedMenuItemId) { this.selectedMenuItemId = selectedMenuItemId; }
    */

    /* TODO: Create getters/setters for selectedDailyMenuId:
    public Long getSelectedDailyMenuIdFunction() { return selectedDailyMenuId; }
    public void setSelectedDailyMenuIdFunction(Long selectedDailyMenuId) { this.selectedDailyMenuId = selectedDailyMenuId; }
    */

    /* TODO: Create getters/setters for selectedCarteMenuId:
    public Long getSelectedCarteMenuIdFunction() { return selectedCarteMenuId; }
    public void setSelectedCarteMenuIdFunction(Long selectedCarteMenuId) { this.selectedCarteMenuId = selectedCarteMenuId; }
    */

    /* TODO: Create getters/setters for selectedMenuItem:
    public MenuItem getSelectedMenuItemFunction() { return selectedMenuItem; }
    public void setSelectedMenuItemFunction(MenuItem selectedMenuItem) { this.selectedMenuItem = selectedMenuItem; }
    */

    /* TODO: Create getters/setters for selectedDailyMenu:
    public DailyMenu getSelectedDailyMenuFunction() { return selectedDailyMenu; }
    public void setSelectedDailyMenuFunction(DailyMenu selectedDailyMenu) { this.selectedDailyMenu = selectedDailyMenu; }
    */

    /* TODO: Create getters/setters for selectedCarteMenu:
    public CarteMenu getSelectedCarteMenuFunction() { return selectedCarteMenu; }
    public void setSelectedCarteMenuFunction(CarteMenu selectedCarteMenu) { this.selectedCarteMenu = selectedCarteMenu; }
    */
}

