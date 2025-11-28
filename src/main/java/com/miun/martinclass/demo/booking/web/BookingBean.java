package com.miun.martinclass.demo.booking.web;

import com.miun.martinclass.demo.booking.service.AvailableBookingService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Named("bookingBean")
@RequestScoped
public class BookingBean {

    @Inject
    private AvailableBookingService availableBookingService;

    private int groupSize;
    private LocalDate selectedDate;
    private List<LocalTime> availableTimes;

    public void loadAvailableTimes() {
        if (groupSize > 0 && selectedDate != null) {
            availableTimes = availableBookingService
                    .getAvailableBookingForDate(selectedDate, groupSize);
        }
    }

    // Getters & setters
    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public List<LocalTime> getAvailableTimes() {
        return availableTimes;
    }
}
