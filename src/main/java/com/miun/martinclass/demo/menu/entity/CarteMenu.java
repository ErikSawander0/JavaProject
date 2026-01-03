package com.miun.martinclass.demo.menu.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "carte_menus")
public class CarteMenu{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean active;
    @ManyToMany
    @JoinTable(
            name = "weekly_menu_items",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<MenuItem> menuItems;

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Long getId() {

        return this.id;
    }

    public void setId(long l) {
        this.id = l;
    }

    public void setActive(boolean b) {this.active = b;}

    public String getName(){return this.name;}
    public void setName(String name) {this.name = name;}
    public void setMenuItems(List<MenuItem> items) {
        this.menuItems = items;
    }

    public boolean isActive() {
        return active;
    }

}
