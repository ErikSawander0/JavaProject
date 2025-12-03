package com.miun.martinclass.demo.menu.web;

import com.miun.martinclass.demo.menu.entity.MenuItem;
import com.miun.martinclass.demo.menu.entity.DailyMenu;
import com.miun.martinclass.demo.menu.service.MenuService;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.List;

@Named
@RequestScoped
public class MenuBean implements Serializable {

    @EJB
    private MenuService menuService;

    private String selectedDay;
    private DailyMenu currentDailyMenu;

    // Initialize with today's weekday if it's a weekday, otherwise Monday
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

    public List<MenuItem> getAppetizers() {
        // TODO: Replace with actual database query when ready
        List<MenuItem> appetizers = new java.util.ArrayList<>();

        MenuItem item1 = new MenuItem();
        item1.setName("Gravad Lax");
        item1.setDescription("Hemgravad lax med hovmästarsås, dill och rostad rågsurdeg");
        item1.setPrice(BigDecimal.valueOf(145));
        item1.setIsGlutenFree(false);
        item1.setIsVegan(false);
        item1.setAllergens("Fisk, Senap");
        appetizers.add(item1);

        MenuItem item2 = new MenuItem();
        item2.setName("Toast Skagen");
        item2.setDescription("Handskalade räkor på rostat surdegsbröd med crème fraiche och löjrom");
        item2.setPrice(BigDecimal.valueOf(165));
        item2.setIsGlutenFree(false);
        item2.setIsVegan(false);
        item2.setAllergens("Skaldjur, Ägg");
        appetizers.add(item2);

        MenuItem item3 = new MenuItem();
        item3.setName("Svamprisotto");
        item3.setDescription("Krämig risotto med blandade skogschampinjoner, parmesan och tryffelolja");
        item3.setPrice(BigDecimal.valueOf(135));
        item3.setIsGlutenFree(true);
        item3.setIsVegan(false);
        item3.setAllergens("Mjölk");
        appetizers.add(item3);

        return appetizers;
    }

    public List<MenuItem> getMainCourses() {
        // TODO: Replace with actual database query when ready
        List<MenuItem> mains = new java.util.ArrayList<>();

        MenuItem item1 = new MenuItem();
        item1.setName("Plankstek");
        item1.setDescription("Oxfilé på planka med bearnaisesås, smörslungad potatis och grönsaker");
        item1.setPrice(BigDecimal.valueOf(295));
        item1.setIsGlutenFree(true);
        item1.setIsVegan(false);
        item1.setAllergens("Mjölk, Ägg");
        mains.add(item1);

        MenuItem item2 = new MenuItem();
        item2.setName("Stekt Gös");
        item2.setDescription("Stekt gös med skaldjurssås, citron, färskpotatis och säsongens grönsaker");
        item2.setPrice(BigDecimal.valueOf(265));
        item2.setIsGlutenFree(true);
        item2.setIsVegan(false);
        item2.setAllergens("Fisk, Skaldjur, Mjölk");
        mains.add(item2);

        MenuItem item3 = new MenuItem();
        item3.setName("Wallenbergare");
        item3.setDescription("Klassisk kalvfärsrätt med potatispuré, lingon och gräddsås");
        item3.setPrice(BigDecimal.valueOf(245));
        item3.setIsGlutenFree(false);
        item3.setIsVegan(false);
        item3.setAllergens("Mjölk, Ägg, Gluten");
        mains.add(item3);

        MenuItem item4 = new MenuItem();
        item4.setName("Älgfilé");
        item4.setDescription("Stekt älgfilé med vinbärssås, rotselleri och hasselbackspotatis");
        item4.setPrice(BigDecimal.valueOf(315));
        item4.setIsVegan(false);
        item4.setIsGlutenFree(true);
        item4.setAllergens("Mjölk");
        mains.add(item4);

        MenuItem item5 = new MenuItem();
        item5.setName("Rotfruktsbiff");
        item5.setDescription("Vegetarisk biff på rotfrukter med belugalinser, rostade morötter och chimichurri");
        item5.setPrice(BigDecimal.valueOf(215));
        item5.setIsGlutenFree(true);
        item5.setIsVegan(true);
        item5.setAllergens("");
        mains.add(item5);

        return mains;
    }

    public List<MenuItem> getDesserts() {
        // TODO: Replace with actual database query when ready
        List<MenuItem> desserts = new java.util.ArrayList<>();

        MenuItem item1 = new MenuItem();
        item1.setName("Ostkaka");
        item1.setDescription("Klassisk svensk ostkaka med sylt och grädde");
        item1.setPrice(BigDecimal.valueOf(95));
        item1.setIsGlutenFree(true);
        item1.setIsVegan(false);
        item1.setAllergens("Mjölk, Ägg");
        desserts.add(item1);

        MenuItem item2 = new MenuItem();
        item2.setName("Kladdkaka");
        item2.setDescription("Kladdig chokladkaka med vaniljglass och hallon");
        item2.setPrice(BigDecimal.valueOf(105));
        item2.setIsGlutenFree(false);
        item2.setIsVegan(false);
        item2.setAllergens("Gluten, Mjölk, Ägg");
        desserts.add(item2);

        MenuItem item3 = new MenuItem();
        item3.setName("Panna Cotta");
        item3.setDescription("Italiensk grädddessert med passionsfrukt och mintsocker");
        item3.setPrice(BigDecimal.valueOf(95));
        item3.setIsGlutenFree(true);
        item3.setIsVegan(false);
        item3.setAllergens("Mjölk");
        desserts.add(item3);

        return desserts;
    }

    // ========== Helper Methods ==========

    private LocalDate getDateForDay(String dayName) {
        LocalDate today = LocalDate.now();
        DayOfWeek currentDay = today.getDayOfWeek();
        DayOfWeek targetDay = DayOfWeek.valueOf(dayName);

        // Get the date for the target day in the current week
        int daysToAdd = targetDay.getValue() - currentDay.getValue();

        // If the target day has already passed this week, get next week's date
        if (daysToAdd < 0) {
            daysToAdd += 7;
        }

        return today.plusDays(daysToAdd);
    }

    // ========== Getters & Setters ==========

    public String getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
        this.currentDailyMenu = null; // Reset cache
    }
}