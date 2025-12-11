package com.miun.martinclass.demo.OrderInfo.entity;

import com.miun.martinclass.demo.menu.entity.CarteAtributes;
import com.miun.martinclass.demo.menu.entity.MenuItem;

import java.math.BigDecimal;

public class CarteMenuItem {
    // MenuItem fields
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isVegan;
    private Boolean isGlutenFree;
    private String allergens;

    // CarteAttributes fields
    private Long carteAttributesId;
    private Integer activeTime;
    private Integer waitingTime;
    private Boolean isMeat;
    private Boolean isDessert;
    private Boolean canSubstitute;
    private Boolean isAppetizer;
    private Boolean isHuvud;

    // Constructors
    public CarteMenuItem() {
    }
    public CarteMenuItem(Long id, String name, String description, BigDecimal price,
                         Boolean isVegan, Boolean isGlutenFree, String allergens,
                         Long carteAttributesId, Integer activeTime, Integer waitingTime,
                         Boolean isMeat, Boolean isDessert,
                         Boolean canSubstitute, Boolean isAppetizer, Boolean isHuvud) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isVegan = isVegan;
        this.isGlutenFree = isGlutenFree;
        this.allergens = allergens;
        this.carteAttributesId = carteAttributesId;
        this.activeTime = activeTime;
        this.waitingTime = waitingTime;
        this.isMeat = isMeat;
        this.isDessert = isDessert;
        this.canSubstitute = canSubstitute;
        this.isAppetizer = isAppetizer;
        this.isHuvud = isHuvud;
    }

    public CarteMenuItem(MenuItem menuItem, CarteAtributes carteAtributes) {
        // MenuItem fields
        this.id = menuItem.getId();
        this.name = menuItem.getName();
        this.description = menuItem.getDescription();
        this.price = menuItem.getPrice();
        this.isVegan = menuItem.getIsVegan();
        this.isGlutenFree = menuItem.getIsGlutenFree();
        this.allergens = menuItem.getAllergens();

        // CarteAttributes fields (if present)
        if (carteAtributes != null) {
            this.carteAttributesId = carteAtributes.getId();
            this.activeTime = carteAtributes.getActiveTime();
            this.waitingTime = carteAtributes.getWaitingTime();
            this.isMeat = carteAtributes.getIsMeat();
            this.isDessert = carteAtributes.getIsDessert();
            this.canSubstitute = carteAtributes.getCanSubstitute();
            this.isAppetizer = carteAtributes.getIsAppetizer();
            this.isHuvud = carteAtributes.getIsHuvud();
        }
    }

    public CarteMenuItem(MenuItem menuItem) {
        this(menuItem, null);
    }

    // Getters - MenuItem fields
    public Long getId() {
        return id;
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

    // Getters - CarteAttributes fields
    public Long getCarteAttributesId() {
        return carteAttributesId;
    }

    public Integer getActiveTime() {
        return activeTime;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public Boolean getIsMeat() {
        return isMeat;
    }

    public Boolean getIsDessert() {
        return isDessert;
    }

    public Boolean getCanSubstitute() {
        return canSubstitute;
    }

    public Boolean getIsAppetizer() {
        return isAppetizer;
    }

    public Boolean getIsHuvud() {
        return isHuvud;
    }

    // Setters - MenuItem fields
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

    // Setters - CarteAttributes fields
    public void setCarteAttributesId(Long carteAttributesId) {
        this.carteAttributesId = carteAttributesId;
    }

    public void setActiveTime(Integer activeTime) {
        this.activeTime = activeTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setIsMeat(Boolean isMeat) {
        this.isMeat = isMeat;
    }

    public void setIsDessert(Boolean isDessert) {
        this.isDessert = isDessert;
    }

    public void setCanSubstitute(Boolean canSubstitute) {
        this.canSubstitute = canSubstitute;
    }

    public void setIsAppetizer(Boolean isAppetizer) {
        this.isAppetizer = isAppetizer;
    }

    public void setIsHuvud(Boolean isHuvud) {
        this.isHuvud = isHuvud;
    }

    // Convenience methods
    public Integer getTotalTime() {
        if (activeTime != null && waitingTime != null) {
            return activeTime + waitingTime;
        }
        return null;
    }

    public boolean hasCarteAttributes() {
        return carteAttributesId != null;
    }
}