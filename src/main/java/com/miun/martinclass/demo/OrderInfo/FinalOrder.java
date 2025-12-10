package com.miun.martinclass.demo.OrderInfo;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

class SimpleOrder{//TODO delete me, represents what was told to be MenuItem

}

@Entity
public class FinalOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long FOid;
    @Transient
    private SimpleOrder original;

    @ElementCollection
    private List<String> selectedAllergens;

    private String orderInstructions;
    private String orderMODType;
    private int orderAmmount;
    private boolean isComplete;
    private LocalDateTime time;

    public long getFOid() {
        return FOid;
    }

    public void setFOid(long FOid) {
        this.FOid = FOid;
    }

    public SimpleOrder getOriginal() {
        return original;
    }

    public void setOriginal(SimpleOrder original) {
        this.original = original;
    }

    public List<String> getSelectedAllergens() {
        return selectedAllergens;
    }

    public void setSelectedAllergens(List<String> selectedAllergens) {
        this.selectedAllergens = selectedAllergens;
    }

    public String getOrderInstructions() {
        return orderInstructions;
    }

    public void setOrderInstructions(String orderInstructions) {
        this.orderInstructions = orderInstructions;
    }

    public String getOrderMODType() {
        return orderMODType;
    }

    public void setOrderMODType(String orderMODType) {
        this.orderMODType = orderMODType;
    }

    public int getOrderAmmount() {
        return orderAmmount;
    }

    public void setOrderAmmount(int orderAmmount) {
        this.orderAmmount = orderAmmount;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
