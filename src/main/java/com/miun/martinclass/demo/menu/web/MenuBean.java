package com.miun.martinclass.demo.menu.web;

import com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem;
import com.miun.martinclass.demo.menu.entity.DailyMenu;
import com.miun.martinclass.demo.menu.service.MenuService;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class MenuBean implements Serializable {

    @EJB
    private MenuService menuService;

    private String selectedDay;
    private DailyMenu currentDailyMenu;
    private List<CarteMenuItem> activeCarteItems;

    public MenuBean() {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            selectedDay = "MONDAY";
        } else {
            selectedDay = dayOfWeek.name();
        }
    }

    // ========== Day Selection (for Design 1 & 2) ==========

    public void selectDay(String day) {
        this.selectedDay = day;
    }

    public DailyMenu getCurrentDailyMenu() {
        if (currentDailyMenu == null && selectedDay != null) {
            LocalDate date = getDateForDay(selectedDay);
            currentDailyMenu = menuService.getDailyMenuForDate(date);
        }
        return currentDailyMenu;
    }

    // ========== Weekly Grid Methods (for Design 3) ==========

    public DailyMenu getMondayMenu() {
        return menuService.getDailyMenuForDate(getDateForDay("MONDAY"));
    }

    public DailyMenu getTuesdayMenu() {
        return menuService.getDailyMenuForDate(getDateForDay("TUESDAY"));
    }

    public DailyMenu getWednesdayMenu() {
        return menuService.getDailyMenuForDate(getDateForDay("WEDNESDAY"));
    }

    public DailyMenu getThursdayMenu() {
        return menuService.getDailyMenuForDate(getDateForDay("THURSDAY"));
    }

    public DailyMenu getFridayMenu() {
        return menuService.getDailyMenuForDate(getDateForDay("FRIDAY"));
    }

    public boolean isToday(String day) {
        LocalDate today = LocalDate.now();
        return today.getDayOfWeek().name().equals(day);
    }

    // ========== À la Carte Methods ==========

    private List<CarteMenuItem> getActiveCarteItems() {
        if (activeCarteItems == null) {
            activeCarteItems = menuService.getActiveCarteMenuItems();
        }
        return activeCarteItems;
    }

    public List<CarteMenuItem> getAppetizers() {
        return getActiveCarteItems().stream()
                .filter(item -> Boolean.TRUE.equals(item.getIsAppetizer()))
                .collect(Collectors.toList());
    }

    public List<CarteMenuItem> getMainCourses() {
        return getActiveCarteItems().stream()
                .filter(item -> Boolean.TRUE.equals(item.getIsHuvud()))
                .collect(Collectors.toList());
    }

    public List<CarteMenuItem> getDesserts() {
        return getActiveCarteItems().stream()
                .filter(item -> Boolean.TRUE.equals(item.getIsDessert()))
                .collect(Collectors.toList());
    }

    // ========== Helper Methods ==========
    private LocalDate getDateForDay(String dayName) {
        LocalDate today = LocalDate.now();
        DayOfWeek currentDay = today.getDayOfWeek();
        DayOfWeek targetDay = DayOfWeek.valueOf(dayName);

        // Get the Monday of the current week
        LocalDate monday = today.minusDays(currentDay.getValue() - DayOfWeek.MONDAY.getValue());

        // Add days to get to the target day
        return monday.plusDays(targetDay.getValue() - DayOfWeek.MONDAY.getValue());
    }


    // ========== Getters & Setters ==========

    public String getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
        this.currentDailyMenu = null;
    }
}