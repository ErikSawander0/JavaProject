package com.miun.martinclass.demo.bookings.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import com.miun.martinclass.demo.bookings.entity.Booking;

public class AvailableBookingService {
    private BookingService bookingService;

    /*
    *  This needs to take the contact info, name, party size, date, assign a table number, then add it to the db,
    *  use the methods from BookingService.
     */
    void confirmBooking() {

    }

    /*
    * These need to take party size, look through the db, and see what times we can fit them in
    * Note that if you need something from the db, use the crud methods in BookingService
    * How complicated this needs to be depends on how realistic we want to make this.
    * for example, we can keep track of how many seats each table id can handle.
    * say a party has 10 people, only table 3 can handle 10 people, and it's already booked.
    * what about pushing tables together?
    * also note these only need to return if a time slot is available for a specific group size,
    * not what table/how many tables and so on.
     */
    List<LocalTime> getAvailableBookingForDate(LocalDate date, int partySize) {
        LocalTime time1 = LocalTime.of(18, 00);
        LocalTime time2 = LocalTime.of(18, 30);
        LocalTime time3 = LocalTime.of(19, 00);
        return List.of(time1, time2, time3);
    }

    List<LocalTime> getTodaysAvailableBookings(int partySize) {
        return getAvailableBookingForDate(LocalDate.now(), partySize);
    }
}
