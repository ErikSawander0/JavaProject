package com.miun.martinclass.demo.bookings.service;

import com.miun.martinclass.demo.bookings.entity.Booking;
import com.miun.martinclass.demo.menu.entity.DailyMenu;
import com.miun.martinclass.demo.menu.entity.MenuItem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class BookingService {
    @PersistenceContext(unitName = "default")
    private EntityManager em;
    // ========== Booking CRUD ==========

    /**
     * Get all bookings
     */
    public List<Booking> getAllBookings() {
        return em.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
    }

    /**
     * Find a specific booking by ID
     */
    public Booking findBookingItemById(Long id) {
        return em.find(Booking.class, id);
    }

    /**
     * Create a new booking
     */
    public void createBooking(Booking item) {
        em.persist(item);
    }

    /**
     * Update an existing booking
     */
    public void updateBooking(Booking item) {
        em.merge(item);
    }

    /**
     * Delete a booking
     */
    public void deleteBooking(Long id) {
        Booking item = em.find(Booking.class, id);
        if(item != null) {
            em.remove(item);
        }
    }


    // ========== Key Query Methods ==========

    /**
     * Get the days bookings. TODO: replace this with the actual db interaction.
     */
    public List<Booking> getTodaysBookings() {
        LocalDate today = LocalDate.now();

        Booking booking1 = new Booking();
        booking1.dateTime = today.atTime(12, 30);
        booking1.numberOfPeople = 4;
        booking1.tableNum = 5;
        booking1.phoneNumber = "+46701234567";
        booking1.name = "Erik Andersson";

        Booking booking2 = new Booking();
        booking2.dateTime = today.atTime(18, 0);
        booking2.numberOfPeople = 2;
        booking2.tableNum = 3;
        booking2.phoneNumber = "+46709876543";
        booking2.name = "Maria Svensson";

        Booking booking3 = new Booking();
        booking3.dateTime = today.atTime(19, 30);
        booking3.numberOfPeople = 6;
        booking3.tableNum = 8;
        booking3.phoneNumber = "+46702345678";
        booking3.name = "Johan Karlsson";

        return List.of(booking1, booking2, booking3);
    }

    /**
     * Get the days bookings for a specific date TODO: replace this with the actual db interaction.
     */
    public List<Booking> getBookingsForDate(LocalDate date) {
        Booking booking1 = new Booking();
        booking1.dateTime = date.atTime(13, 0);
        booking1.numberOfPeople = 3;
        booking1.tableNum = 2;
        booking1.phoneNumber = "+46705551234";
        booking1.name = "Anna Lindström";

        Booking booking2 = new Booking();
        booking2.dateTime = date.atTime(17, 30);
        booking2.numberOfPeople = 5;
        booking2.tableNum = 7;
        booking2.phoneNumber = "+46708889999";
        booking2.name = "Peter Bergman";

        return List.of(booking1, booking2);
    }
}
