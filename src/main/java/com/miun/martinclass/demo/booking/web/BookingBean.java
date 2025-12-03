package com.miun.martinclass.demo.booking.web;

import com.miun.martinclass.demo.booking.service.BookingService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("bookingBean")
@SessionScoped
public class BookingBean implements Serializable {

    @Inject
    private BookingService bookingService;

    private String selectedDateString;
    private String selectedTime;

    private String name;
    private String email;
    private String phone;

    private String message;
    private boolean success;

    private List<String> availableTimes = new ArrayList<>();

    // 🔵 Kalenderns dagar
    private List<String> days;

    // ---------------------------------------------------------
    // INIT – genererar alla dagar i nuvarande månad
    // ---------------------------------------------------------
    @PostConstruct
    public void init() {
        days = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);
        int monthLength = today.lengthOfMonth();

        for (int i = 1; i <= monthLength; i++) {
            LocalDate d = firstDay.withDayOfMonth(i);
            days.add(d.toString()); // Ex: "2025-12-11"
        }
    }

    public List<String> getDays() {
        return days;
    }

    // ---------------------------------------------------------
    // ANROPAS FRÅN JS (booking.js) MED BOKADE TIDER
    // ---------------------------------------------------------
    public void loadTimesFromJS(List<String> bookedTimes) {

        if (selectedDateString == null) return;

        LocalDate date = LocalDate.parse(selectedDateString);

        availableTimes = bookingService.generateTimes(date, bookedTimes);
    }

    // ---------------------------------------------------------
    // ANROPAS FRÅN XHTML VIA HIDDEN BUTTON (update times)
    // ---------------------------------------------------------
    public void updateDateFromString() {
        // Endast för att trigga JSF att uppdatera UI efter datumval
    }

    // ---------------------------------------------------------
    // ANROPAS FRÅN XHTML när JSF måste uppdatera tider
    // ---------------------------------------------------------
    public void loadTimesJSF() {
        // Denna metod behövs för att <f:ajax> ska fungera!
        // Låter JSF uppdatera dropdown-menyn efter JavaScript-anropet
    }

    // ---------------------------------------------------------
    // SPARA BOKNINGEN
    // ---------------------------------------------------------
    public String submitBooking() {

        if (selectedDateString == null || selectedTime == null) {
            message = "Välj datum och tid.";
            success = false;
            return null;
        }

        message = bookingService.saveBooking(
                selectedDateString, selectedTime, name, email, phone
        );

        success = message.startsWith("Tack");
        return null;
    }

    // ---------------------------------------------------------
    // Getter & Setter
    // ---------------------------------------------------------
    public String getSelectedDateString() { return selectedDateString; }
    public void setSelectedDateString(String selectedDateString) { this.selectedDateString = selectedDateString; }

    public String getSelectedTime() { return selectedTime; }
    public void setSelectedTime(String selectedTime) { this.selectedTime = selectedTime; }

    public List<String> getAvailableTimes() { return availableTimes; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }
}
