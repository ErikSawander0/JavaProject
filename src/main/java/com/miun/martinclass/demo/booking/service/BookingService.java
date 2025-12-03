package com.miun.martinclass.demo.booking.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookingService {

    @Inject
    private AvailableBookingService availableService;

    // Mock-databas: lagras i RAM (byter senare till JDBC)
    private final List<BookingRecord> bookings = new ArrayList<>();

    // Liten rekordklass för lagring
    public record BookingRecord(String date, String time, String name, String email, String phone) {}

    // ---------------------------------------------------------
    // HÄMTA ALLA TIDER SOM ÄR BOKADE FÖR ETT DATUM (ex "2025-02-15")
    // ---------------------------------------------------------
    public List<String> getBookedTimes(String date) {
        List<String> result = new ArrayList<>();

        for (BookingRecord r : bookings) {
            if (r.date().equals(date)) {
                result.add(r.time());  // ex "11:00"
            }
        }
        return result;
    }

    // ---------------------------------------------------------
    // GENERERA LEDIGA TIDER BASERAT PÅ BOOKED + ÖPPETTIDER
    // ---------------------------------------------------------
    public List<String> generateTimes(LocalDate date, List<String> booked) {

        List<LocalTime> all = availableService.getAvailableBookingForDate(date);
        List<String> result = new ArrayList<>();

        for (LocalTime t : all) {

            // Formatera till "HH:mm"
            String formatted = t.toString().length() == 5
                    ? t.toString()
                    : t.toString().substring(0,5);

            // Hoppa över upptagna tider
            if (booked.contains(formatted)) continue;

            result.add(formatted);
        }

        return result;
    }

    // ---------------------------------------------------------
    // SPARA BOKNING
    // ---------------------------------------------------------
    public String saveBooking(String date, String time, String name, String email, String phone) {

        // 1️⃣ Kolla om tiden redan är bokad
        for (BookingRecord r : bookings) {
            if (r.date().equals(date) && r.time().equals(time)) {
                return "Tiden är redan bokad.";
            }
        }

        // 2️⃣ Spara bokningen
        bookings.add(new BookingRecord(date, time, name, email, phone));

        return "Tack! Din bokning är registrerad.";
    }
}
