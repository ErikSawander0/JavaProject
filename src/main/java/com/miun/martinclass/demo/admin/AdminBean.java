package com.miun.martinclass.demo.admin;

import com.miun.martinclass.demo.OrderInfo.service.CarteService;
import com.miun.martinclass.demo.blog.entity.BlogEntry;
import com.miun.martinclass.demo.blog.service.BlogService;
import com.miun.martinclass.demo.menu.entity.CarteMenu;
import com.miun.martinclass.demo.menu.entity.DailyMenu;
import com.miun.martinclass.demo.menu.entity.MenuItem;
import com.miun.martinclass.demo.menu.service.MenuService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

@Named("adminBean")
@ViewScoped
public class AdminBean implements Serializable {

    @Inject
    private MenuService menuService;

    @Inject
    private CarteService carteService;

    @Inject
    private BlogService blogService;


    private List<MenuItem> menuItems;
    private List<DailyMenu> dailyMenus;
    private List<CarteMenu> carteMenus;
    private List<BlogEntry> blogEntries;

    private MenuItem selectedMenuItem;
    private DailyMenu selectedDailyMenu;
    private CarteMenu selectedCarteMenu;
    private BlogEntry selectedBlogEntry;

    private MenuItem newMenuItem = new MenuItem();
    private DailyMenu newDailyMenu = new DailyMenu();
    private CarteMenu newCarteMenu = new CarteMenu();
    private BlogEntry newBlogEntry = new BlogEntry();
    private Part uploadedPicture;

    private Long selectedMenuItemId;
    private Long selectedDailyMenuId;
    private Long selectedCarteMenuId;
    private Long selectedBlogEntryId;

    @PostConstruct
    public void init() {
        reloadData();
    }

    private void reloadData() {
        menuItems = menuService.getAllMenuItems();
        dailyMenus = menuService.getAllDailyMenus();
        carteMenus = carteService.getAllCarteMenus();
        blogEntries = blogService.getAllBlogEntries();
    }

    public void addMenuItem() {
        menuService.createMenuItem(newMenuItem);
        newMenuItem = new MenuItem();
        reloadData();
    }

    public void updateMenuItem() {
        menuService.updateMenuItem(selectedMenuItem);
        reloadData();
    }

    public void removeMenuItem(MenuItem item) {
        menuService.deleteMenuItem(item.getId());
        reloadData();
    }

    public void addDailyMenu() {
        menuService.createDailyMenu(newDailyMenu);
        System.out.println("Inserting with date " + newDailyMenu.getDate());
        newDailyMenu = new DailyMenu();
        reloadData();
    }

    public void removeDailyMenu(DailyMenu menu) {
        menuService.deleteDailyMenu(menu.getId());
        reloadData();
    }

    public void addMenuItemToDailyMenu() {
        if (selectedMenuItemId != null && selectedDailyMenuId != null) {
            menuService.addItemToDailyMenu(
                    selectedDailyMenuId, selectedMenuItemId

            );
            reloadData();
        }
    }

    public void removeMenuItemFromDailyMenu(
            DailyMenu dailyMenu,
            MenuItem menuItem
    ) {
        menuService.removeItemFromDailyMenu(
                menuItem.getId(),
                dailyMenu.getId()
        );
        reloadData();
    }

    // ---- CarteMenu CRUD ----
    public void addCarteMenu() {
        carteService.createCarteMenu(newCarteMenu);
        newCarteMenu = new CarteMenu();
        reloadData();
    }

    public void removeCarteMenu(CarteMenu menu) {
        carteService.deleteCarteMenu(menu.getId());
        reloadData();
    }

    // ---- Add/Remove MenuItem from CarteMenu ----
    public void addMenuItemToCarteMenu() {
        if (selectedCarteMenuId != null && selectedMenuItemId != null) {
            carteService.addItemToCarteMenu(selectedCarteMenuId, selectedMenuItemId);
            reloadData();
        }
    }

    public void removeMenuItemFromCarteMenu(CarteMenu menu, MenuItem item) {
        carteService.removeItemFromCarteMenu(menu.getId(), item.getId());
        reloadData();
    }

    public void updateCarteAttributes(MenuItem item) {
        menuService.updateMenuItem(item);
        reloadData();
    }

    public Part getUploadedPicture() {
        return uploadedPicture;
    }

    public void setUploadedPicture(Part picture) {
        this.uploadedPicture = picture;
    }

    public void addBlogEntry() {
        //https://stackoverflow.com/questions/27677397/how-to-upload-file-using-jsf-2-2-hinputfile-where-is-the-saved-file
        try (InputStream input = uploadedPicture.getInputStream()) {
            newBlogEntry.setPicture(input.readAllBytes());
        }
        catch (IOException e) {
            // Show faces message?
        }
        blogService.saveBlogEntry(newBlogEntry);
        newBlogEntry = new BlogEntry();
        reloadData();
    }

    public void removeBlogEntry(BlogEntry entry) {
        blogService.deleteBlogEntryById(entry.getId());
        reloadData();
    }
    public String getPictureAsBase64(byte[] picture) {
        if (picture == null || picture.length == 0) {
            return ""; // optionally return a placeholder image
        }
        return Base64.getEncoder().encodeToString(picture);
    }

    // Collections
    public List<MenuItem> getMenuItems() { return menuItems; }
    public List<DailyMenu> getDailyMenus() { return dailyMenus; }
    public List<CarteMenu> getCarteMenus() { return carteMenus; }
    public List<BlogEntry> getBlogEntries() {
        return blogEntries;
    }


    // New objects (for creating new items)
    public MenuItem getNewMenuItem() { return newMenuItem; }
    public void setNewMenuItem(MenuItem newMenuItem) {
        this.newMenuItem = newMenuItem;
    }

    public DailyMenu getNewDailyMenu() { return newDailyMenu; }
    public void setNewDailyMenu(DailyMenu newDailyMenu) {
        this.newDailyMenu = newDailyMenu;
    }

    public CarteMenu getNewCarteMenu() { return newCarteMenu; }
    public void setNewCarteMenu(CarteMenu newCarteMenu) {
        this.newCarteMenu = newCarteMenu;
    }

    public BlogEntry getNewBlogEntry() {
        return newBlogEntry;
    }
    public void setNewBlogEntry(BlogEntry newBlogEntry) {
        this.newBlogEntry = newBlogEntry;
    }

    // Selected IDs (for dropdowns or selects)
    public Long getSelectedMenuItemId() { return selectedMenuItemId; }
    public void setSelectedMenuItemId(Long selectedMenuItemId) {
        this.selectedMenuItemId = selectedMenuItemId;
    }

    public Long getSelectedDailyMenuId() { return selectedDailyMenuId; }
    public void setSelectedDailyMenuId(Long selectedDailyMenuId) {
        this.selectedDailyMenuId = selectedDailyMenuId;
    }

    public Long getSelectedCarteMenuId() { return selectedCarteMenuId; }
    public void setSelectedCarteMenuId(Long selectedCarteMenuId) {
        this.selectedCarteMenuId = selectedCarteMenuId;
    }

    public Long getSelectedBlogEntryId() {
        return selectedBlogEntryId;
    }
    public void setSelectedBlogEntryId(Long selectedBlogEntryId) {
        this.selectedBlogEntryId = selectedBlogEntryId;
    }

    // Selected objects (if used in XHTML)
    public MenuItem getSelectedMenuItem() { return selectedMenuItem; }
    public void setSelectedMenuItem(MenuItem selectedMenuItem) {
        this.selectedMenuItem = selectedMenuItem;
    }

    public DailyMenu getSelectedDailyMenu() { return selectedDailyMenu; }
    public void setSelectedDailyMenu(DailyMenu selectedDailyMenu) {
        this.selectedDailyMenu = selectedDailyMenu;
    }

    public CarteMenu getSelectedCarteMenu() { return selectedCarteMenu; }
    public void setSelectedCarteMenu(CarteMenu selectedCarteMenu) {
        this.selectedCarteMenu = selectedCarteMenu;
    }

    public BlogEntry getSelectedBlogEntry() {
        return selectedBlogEntry;
    }
    public void setSelectedBlogEntry(BlogEntry selectedBlogEntry) {
        this.selectedBlogEntry = selectedBlogEntry;
    }
}