package com.miun.martinclass.demo.bookings.web;

import com.miun.martinclass.demo.bookings.service.AvailableBookingService;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BookingBean implements Serializable {

    private AvailableBookingService availableBookingService;

    private int partySize;
    private LocalDate selectedDate;
    private LocalTime selectedTime;
    private String customerName;
    private String phoneNumber;
    private List<DateOption> availableDates;
    private List<LocalTime> availableTimes;
    private boolean showDateSelection = false;
    private boolean showTimeSelection = false;
    private boolean showConfirmation = false;
    private String errorMessage;

    public void startBooking() {
        errorMessage = null;
        if (partySize < 1) {
            errorMessage = "Please enter a valid party size";
            return;
        }
        if (partySize > 6) {
            errorMessage = "Maximum party size is 6 people";
            return;
        }

        generateAvailableDates();
        showDateSelection = true;
        showTimeSelection = false;
        showConfirmation = false;
    }

    private void generateAvailableDates() {
        availableDates = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 14; i++) {
            LocalDate date = today.plusDays(i);
            List<LocalTime> times = availableBookingService.getAvailableBookingForDate(date, partySize);
            boolean fullyBooked = times.isEmpty();

            availableDates.add(new DateOption(date, fullyBooked));
        }
    }

    public void selectDate(LocalDate date) {
        this.selectedDate = date;
        this.availableTimes = availableBookingService.getAvailableBookingForDate(date, partySize);
        this.showTimeSelection = true;
        this.selectedTime = null;
    }

    public void selectTime(LocalTime time) {
        this.selectedTime = time;
    }

    public void confirmBooking() {
        errorMessage = null;

        if (customerName == null || customerName.trim().isEmpty()) {
            errorMessage = "Please enter your name";
            return;
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            errorMessage = "Please enter your phone number";
            return;
        }
        if (selectedTime == null) {
            errorMessage = "Please select a time";
            return;
        }

        LocalDateTime dateTime = LocalDateTime.of(selectedDate, selectedTime);
        availableBookingService.confirmBooking(dateTime, partySize, customerName, phoneNumber);

        showConfirmation = true;
        showDateSelection = false;
        showTimeSelection = false;
    }

    public void reset() {
        partySize = 0;
        selectedDate = null;
        selectedTime = null;
        customerName = null;
        phoneNumber = null;
        availableDates = null;
        availableTimes = null;
        showDateSelection = false;
        showTimeSelection = false;
        showConfirmation = false;
        errorMessage = null;
    }

    // Getters and Setters
    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public LocalTime getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(LocalTime selectedTime) {
        this.selectedTime = selectedTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<DateOption> getAvailableDates() {
        return availableDates;
    }

    public List<LocalTime> getAvailableTimes() {
        return availableTimes;
    }

    public boolean isShowDateSelection() {
        return showDateSelection;
    }

    public boolean isShowTimeSelection() {
        return showTimeSelection;
    }

    public boolean isShowConfirmation() {
        return showConfirmation;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // Inner class for date options
    public static class DateOption {
        private LocalDate date;
        private boolean fullyBooked;

        public DateOption(LocalDate date, boolean fullyBooked) {
            this.date = date;
            this.fullyBooked = fullyBooked;
        }

        public LocalDate getDate() {
            return date;
        }

        public boolean isFullyBooked() {
            return fullyBooked;
        }

        public String getFormattedDate() {
            return date.format(DateTimeFormatter.ofPattern("EEE, MMM d"));
        }
    }
}