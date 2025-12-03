package com.miun.martinclass.demo.bookings.entity;

import com.miun.martinclass.demo.menu.entity.DailyMenu;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
// TODO: all of this was left as public for the dummy variables, change this to private for later.
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public LocalDateTime dateTime;
    public int numberOfPeople;
    public int tableNum;
    public String phoneNumber;
    public String name;
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public int getNumberOfPeople() {
        return numberOfPeople;
    }
    public int getTableNum() {
        return tableNum;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getName() {
        return name;
    }
}
