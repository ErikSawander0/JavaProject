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

    private LocalDate date;

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
        this.id = l;
    }

    public void setDate(LocalDate now) {
        this.date = now;
    }

    public void setMenuItems(List<MenuItem> items) {
        this.menuItems = items;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

}