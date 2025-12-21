package com.miun.martinclass.demo.menu.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isVegan;
    private Boolean isGlutenFree;
    private String allergens;

    @ManyToMany(mappedBy = "menuItems")
    private List<DailyMenu> weeklyMenus;

    @ManyToMany(mappedBy = "menuItems")
    private List<CarteMenu> carteMenus;

    @OneToOne(mappedBy = "menuItem", fetch = FetchType.LAZY)
    private CarteAtributes carteAtributes;
    // Getters
    public Long getId() {
        return id;
    }

    public CarteAtributes getCarteAtributes() {
        return carteAtributes;
    }

    public void setCarteAtributes(CarteAtributes carteAtributes) {
        this.carteAtributes = carteAtributes;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getIsVegan() {
        return isVegan;
    }

    public Boolean getIsGlutenFree() {
        return isGlutenFree;
    }

    public String getAllergens() {
        return allergens;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setIsVegan(Boolean isVegan) {
        this.isVegan = isVegan;
    }

    public void setIsGlutenFree(Boolean isGlutenFree) {
        this.isGlutenFree = isGlutenFree;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

}