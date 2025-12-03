package com.miun.martinclass.demo.booking.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AvailableBookingService {

    public List<LocalTime> getAvailableBookingForDate(LocalDate date) {

        List<LocalTime> times = new ArrayList<>();
        LocalTime start;
        LocalTime end;

        switch (date.getDayOfWeek()) {
            case SUNDAY -> { return times; } // stängt
            case SATURDAY -> {
                start = LocalTime.of(11, 0);
                end = LocalTime.of(16, 30);
            }
            default -> {
                start = LocalTime.of(9, 0);
                end = LocalTime.of(18, 0);
            }
        }

        LocalTime t = start;
        while (!t.isAfter(end)) {
            times.add(t);
            t = t.plusMinutes(30);
        }

        return times;
    }
}
