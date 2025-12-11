package com.miun.martinclass.demo.OrderInfo.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
import java.util.List;

@Embeddable
public class SimpleOrder {
    private Long id;
    private String name;
    private String selectedAllergens;
    private String specialInstructions;
    private String comments;
    int quantity;
    LocalDateTime orderedAt;

    public String getName() {
        return name;
    }
    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }
    public Long getId() {
        return id;
    }
    public String getSelectedAllergens() {
        return selectedAllergens;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getComments() {
        return comments;
    }
    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setSelectedAllergens(String selectedAllergens) {
        this.selectedAllergens = selectedAllergens;
    }
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
}
