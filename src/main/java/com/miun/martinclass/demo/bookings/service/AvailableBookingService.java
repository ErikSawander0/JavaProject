package com.miun.martinclass.demo.bookings.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import com.miun.martinclass.demo.bookings.entity.Booking;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;

@Stateless
public class AvailableBookingService {
    @EJB
    private BookingService bookingService;

    /*
    *  This needs to take the contact info, name, party size, date, assign a table number, then add it to the db,
    *  use the methods from BookingService.
     */
    public void confirmBooking(LocalDateTime date, int partySize, String name, String phoneNumber) {
        List<Booking> todaysBookings =  bookingService.getBookingsForDate(date.toLocalDate());
        int numberOfBookings = todaysBookings.size();
        if(numberOfBookings > 12) {
            return;
        }
        Booking booking = new Booking();
        booking.dateTime = date;
        booking.name = name;
        booking.numberOfPeople = partySize;
        booking.phoneNumber = phoneNumber;
        booking.tableNum = numberOfBookings + 1;
        bookingService.createBooking(booking);
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

    /*  For now we will use 12 tables, with a capacity for 6 people, we can update this later. */
    public List<LocalTime> getAvailableBookingForDate(LocalDate date, int partySize) {
        List<Booking> todaysBookings =  bookingService.getBookingsForDate(date);
        if(todaysBookings.size() < 12 && partySize < 6) {
            LocalTime time1 = LocalTime.of(18, 00);
            LocalTime time2 = LocalTime.of(18, 30);
            LocalTime time3 = LocalTime.of(19, 00);
            LocalTime time4 = LocalTime.of(19, 30);
            LocalTime time5 = LocalTime.of(20, 00);
            LocalTime time6 = LocalTime.of(20, 30);
            LocalTime time7 = LocalTime.of(21, 00);
            return List.of(time1, time2, time3, time4, time5, time6, time7);
        }
        return List.of();
    }

    List<LocalTime> getTodaysAvailableBookings(int partySize) {
        return getAvailableBookingForDate(LocalDate.now(), partySize);
    }
}
