package com.miun.martinclass.demo.menu.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "daily_menus")
public class DailyMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate Date;

    @ManyToMany
    @JoinTable(
            name = "daily_menu_items",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<MenuItem> menuItems;

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setId(long l) {
    }

    public void setDate(LocalDate now) {
        this.Date = now;
    }

    public void setMenuItems(List<MenuItem> items) {
        this.menuItems = items;
    }

    // Getters/setters...

    public Long getId() {
        return id;
    }

}