package com.miun.martinclass.demo.scheduling.service;

import com.miun.martinclass.demo.scheduling.entity.Shift;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class ShiftService {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    // ========== Shift CRUD ==========

    /**
     * Get all shifts
     */
    public List<Shift> getAllShifts() {
        return em.createQuery("SELECT s FROM Shift s", Shift.class).getResultList();
    }

    /**
     * Find shift by ID
     */
    public Shift findById(Long id) {
        return em.find(Shift.class, id);
    }

    /**
     * Create a new shift
     */
    public void createShift(Shift shift) {
        em.persist(shift);
    }

    /**
     * Update an existing shift
     */
    public void updateShift(Shift shift) {
        em.merge(shift);
    }

    /**
     * Delete a shift
     */
    public void deleteShift(Long id) {
        Shift shift = em.find(Shift.class, id);
        if (shift != null) {
            em.remove(shift);
        }
    }

    // ========== Query Methods ==========

    /**
     * Get all shifts for a specific date
     */
    public List<Shift> getShiftsByDate(LocalDate date) {
        return em.createQuery(
                        "SELECT s FROM Shift s WHERE s.date = :date",
                        Shift.class)
                .setParameter("date", date)
                .getResultList();
    }

    /**
     * Get all shifts for a specific employee
     */
    public List<Shift> getShiftsByEmployeeId(Long employeeId) {
        return em.createQuery(
                        "SELECT s FROM Shift s WHERE s.employeeId = :employeeId",
                        Shift.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }

    /**
     * Get shifts filtered by date and/or employee
     */
    public List<Shift> getShiftsFiltered(LocalDate date, Long employeeId) {
        if (date != null && employeeId != null) {
            return em.createQuery(
                            "SELECT s FROM Shift s WHERE s.date = :date AND s.employeeId = :employeeId",
                            Shift.class)
                    .setParameter("date", date)
                    .setParameter("employeeId", employeeId)
                    .getResultList();
        } else if (date != null) {
            return getShiftsByDate(date);
        } else if (employeeId != null) {
            return getShiftsByEmployeeId(employeeId);
        } else {
            return getAllShifts();
        }
    }

    /**
     * Check if an employee already has a shift on a specific date AND shift type
     * Used for conflict prevention - employees CAN work both afternoon and night same day
     * but CANNOT work two afternoon shifts or two night shifts on same date
     */
    public boolean employeeHasShiftOnDateAndType(Long employeeId, LocalDate date, Shift.ShiftType shiftType) {
        Long count = em.createQuery(
                        "SELECT COUNT(s) FROM Shift s WHERE s.employeeId = :employeeId AND s.date = :date AND s.shiftType = :shiftType",
                        Long.class)
                .setParameter("employeeId", employeeId)
                .setParameter("date", date)
                .setParameter("shiftType", shiftType)
                .getSingleResult();
        return count > 0;
    }
}