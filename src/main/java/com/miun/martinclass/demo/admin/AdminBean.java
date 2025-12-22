package com.miun.martinclass.demo.admin;

import com.miun.martinclass.demo.menu.entity.CarteMenu;
import com.miun.martinclass.demo.menu.entity.DailyMenu;
import com.miun.martinclass.demo.menu.entity.MenuItem;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("adminBean")
@ViewScoped
public class AdminBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<MenuItem> menuItems;
    private List<DailyMenu> dailyMenus;
    private List<CarteMenu> carteMenus;
    private MenuItem newMenuItem = new MenuItem();
    private DailyMenu newDailyMenu = new DailyMenu();
    private CarteMenu newCarteMenu = new CarteMenu();
    private Long selectedMenuItemId;
    private Long selectedDailyMenuId;
    private Long selectedCarteMenuId;


    // INIT — LOAD DUMMY DATA
    @PostConstruct
    public void init() {

        // ------- MENU ITEMS -------
        menuItems = new ArrayList<>();

        MenuItem m1 = new MenuItem();
        m1.setId(1L);
        m1.setName("Plankstek");
        m1.setDescription("Oxfilé med tillbehör");
        m1.setPrice(BigDecimal.valueOf(295));
        m1.setIsVegan(false);

        MenuItem m2 = new MenuItem();
        m2.setId(2L);
        m2.setName("Toast Skagen");
        m2.setDescription("Handskalade räkor");
        m2.setPrice(BigDecimal.valueOf(165));
        m2.setIsVegan(false);

        menuItems.add(m1);
        menuItems.add(m2);


        // ------- DAILY MENUS -------
        dailyMenus = new ArrayList<>();

        DailyMenu dm = new DailyMenu();
        dm.setId(1L);
        dm.setDate(LocalDate.now());
        dm.setMenuItems(new ArrayList<>());

        dm.getMenuItems().add(m1);   // Add dish to todays menu

        dailyMenus.add(dm);


        // ------- CARTE MENUS -------
        carteMenus = new ArrayList<>();

        CarteMenu cm = new CarteMenu();
        cm.setId(1L);
        cm.setName("Vintermeny");
        cm.setMenuItems(new ArrayList<>());

        cm.getMenuItems().add(m2);

        carteMenus.add(cm);
    }

    // MENU ITEM FUNCTIONS
    public void addMenuItem() {
        if (newMenuItem.getName() == null || newMenuItem.getName().isEmpty()) return;

        newMenuItem.setId((long) menuItems.size() + 1);
        menuItems.add(newMenuItem);

        newMenuItem = new MenuItem(); // Reset form
    }

    public void removeMenuItem(MenuItem item) {
        menuItems.remove(item);

        // Remove from daily menus
        for (DailyMenu dm : dailyMenus) {
            dm.getMenuItems().remove(item);
        }

        // Remove from carte menus
        for (CarteMenu cm : carteMenus) {
            cm.getMenuItems().remove(item);
        }
    }


    // DAILY MENU FUNCTIONS
    public void createDailyMenu() {
        newDailyMenu.setId((long) dailyMenus.size() + 1);

        if (newDailyMenu.getDate() == null) {
            newDailyMenu.setDate(LocalDate.now());
        }

        newDailyMenu.setMenuItems(new ArrayList<>());

        dailyMenus.add(newDailyMenu);

        newDailyMenu = new DailyMenu(); // Reset form
    }

    public void removeDailyMenu(DailyMenu menu) {
        dailyMenus.remove(menu);
    }

    public void addMenuItemToDailyMenu() {
        if (selectedDailyMenuId == null || selectedMenuItemId == null) return;

        DailyMenu dailyMenu = dailyMenus.stream()
                .filter(dm -> dm.getId().equals(selectedDailyMenuId))
                .findFirst().orElse(null);

        MenuItem item = menuItems.stream()
                .filter(mi -> mi.getId().equals(selectedMenuItemId))
                .findFirst().orElse(null);

        if (dailyMenu != null && item != null) {
            dailyMenu.getMenuItems().add(item);
        }

        selectedDailyMenuId = null;
        selectedMenuItemId = null;
    }


    // CARTE MENU FUNCTIONS
    public void createCarteMenu() {
        newCarteMenu.setId((long) carteMenus.size() + 1);
        newCarteMenu.setMenuItems(new ArrayList<>());

        carteMenus.add(newCarteMenu);

        newCarteMenu = new CarteMenu(); // Reset
    }

    public void removeCarteMenu(CarteMenu menu) {
        carteMenus.remove(menu);
    }

    public void addItemToCarteMenu() {
        if (selectedCarteMenuId == null || selectedMenuItemId == null) return;

        CarteMenu carteMenu = carteMenus.stream()
                .filter(cm -> cm.getId().equals(selectedCarteMenuId))
                .findFirst().orElse(null);

        MenuItem item = menuItems.stream()
                .filter(mi -> mi.getId().equals(selectedMenuItemId))
                .findFirst().orElse(null);

        if (carteMenu != null && item != null) {
            carteMenu.getMenuItems().add(item);
        }

        selectedCarteMenuId = null;
        selectedMenuItemId = null;
    }



    // GETTERS & SETTERS
    public List<MenuItem> getMenuItems() { return menuItems; }
    public List<DailyMenu> getDailyMenus() { return dailyMenus; }
    public List<CarteMenu> getCarteMenus() { return carteMenus; }

    public MenuItem getNewMenuItem() { return newMenuItem; }
    public DailyMenu getNewDailyMenu() { return newDailyMenu; }
    public CarteMenu getNewCarteMenu() { return newCarteMenu; }

    public Long getSelectedMenuItemId() { return selectedMenuItemId; }
    public void setSelectedMenuItemId(Long id) { this.selectedMenuItemId = id; }

    public Long getSelectedDailyMenuId() { return selectedDailyMenuId; }
    public void setSelectedDailyMenuId(Long id) { this.selectedDailyMenuId = id; }

    public Long getSelectedCarteMenuId() { return selectedCarteMenuId; }
    public void setSelectedCarteMenuId(Long id) { this.selectedCarteMenuId = id; }
}