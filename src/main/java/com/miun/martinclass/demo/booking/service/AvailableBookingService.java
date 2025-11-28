package com.miun.martinclass.demo.booking.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AvailableBookingService {

    /**
     * Mock-version: returnerar fasta tider för demonstration
     * Den här kommer ersättas av riktig logik i JOB 3.
     */
    public List<LocalTime> getAvailableBookingForDate(LocalDate date, int groupSize) {

        List<LocalTime> times = new ArrayList<>();

        // Exempel på restaurangens öppettider
        LocalTime start = LocalTime.of(10, 0);
        LocalTime end   = LocalTime.of(22, 0);

        // Varje 30 minuter: 10:00, 10:30, 11:00...
        LocalTime t = start;
        while (!t.isAfter(end)) {
            times.add(t);
            t = t.plusMinutes(30);
        }

        return times;
    }
}

