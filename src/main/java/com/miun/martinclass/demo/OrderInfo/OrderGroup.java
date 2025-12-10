package com.miun.martinclass.demo.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderGroup {
    private List<FinalOrder> orders = new ArrayList<>();

    private long id;
    private long groupID;
    private boolean isDone;


    public long getID(){
        return id;
    }

    public void setID(long inID){
        this.id = inID;
    }
    public long getGroupID(){
        return id;
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

    public List<FinalOrder> getOrders() {

        return orders;
    }

    public void setOrders(List<FinalOrder> inOrders){
        this.orders = inOrders;
    }
}
