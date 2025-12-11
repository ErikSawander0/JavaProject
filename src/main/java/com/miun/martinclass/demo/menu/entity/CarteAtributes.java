package com.miun.martinclass.demo.menu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carte_attributes")
public class CarteAtributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One-to-one relationship with MenuItem
    @OneToOne
    @JoinColumn(name = "menu_item_id", unique = true)
    private MenuItem menuItem;

    private Integer activeTime;        // minutes
    private Integer waitingTime;        // minutes
    private Boolean isMeat;
    private Boolean isDessert;
    private Boolean canSubstitute;
    private Boolean isAppetizer;
    private Boolean isHuvud;

    // Getters
    public Long getId() {
        return id;
    }

    public Boolean getIsAppetizer() {
        return isAppetizer;
    }

    public Boolean getIsHuvud() {
        return isHuvud;
    }

    public MenuItem getMenuItem() {
        return menuItem;
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

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void setActiveTime(Integer prepTime) {
        this.activeTime = prepTime;
    }

    public void setWaitingTime(Integer cookTime) {
        this.waitingTime = cookTime;
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

}