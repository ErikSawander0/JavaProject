package com.miun.martinclass.demo.bookings.service;

import com.miun.martinclass.demo.bookings.entity.Booking;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
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
        if (item != null) {
            em.remove(item);
        }
    }

    // ========== Key Query Methods ==========

    /**
     * Get today's bookings from the database.
     */
    public List<Booking> getTodaysBookings() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        return em.createQuery(
                        "SELECT b FROM Booking b WHERE b.dateTime BETWEEN :start AND :end",
                        Booking.class)
                .setParameter("start", startOfDay)
                .setParameter("end", endOfDay)
                .getResultList();
    }

    /**
     * Get bookings for a specific date from the database.
     */
    public List<Booking> getBookingsForDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return em.createQuery(
                        "SELECT b FROM Booking b WHERE b.dateTime BETWEEN :start AND :end",
                        Booking.class)
                .setParameter("start", startOfDay)
                .setParameter("end", endOfDay)
                .getResultList();
    }

}
