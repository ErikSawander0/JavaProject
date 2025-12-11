package com.miun.martinclass.demo.OrderInfo.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_groups")
public class OrderGroup {
    @Id
    private Long id;
    @ElementCollection
    private List<SimpleOrder> Orders = new ArrayList<>();

    private long groupID;
    private boolean isDone;

    public long getID(){
        return id;
    }
    public void setID(long inID){
        this.id = inID;
    }

    public long getGroupID(){
        return groupID;
    }
    public void setGroupID(long inGroupID){
        this.groupID = inGroupID;
    }

    public boolean getIsDone() {
        return isDone;
    }
    public void setIsDone(boolean inIsDone) {
        this.isDone = inIsDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<SimpleOrder> getOrders() {
        return Orders;
    }
    public void setOrders(List<SimpleOrder> Orders){
        this.Orders = Orders;
    }
}
