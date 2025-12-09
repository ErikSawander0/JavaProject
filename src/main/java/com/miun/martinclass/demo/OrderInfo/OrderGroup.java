package com.miun.martinclass.demo.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderGroup {
    private List<SimpleOrder> Orders = new ArrayList<>();

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

    public List<SimpleOrder> getOrders() {

        return Orders;
    }
    public void setOrders(List<SimpleOrder> Orders){
        this.Orders = Orders;
    }

}
