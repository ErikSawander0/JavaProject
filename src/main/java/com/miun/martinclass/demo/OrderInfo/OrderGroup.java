package com.miun.martinclass.demo.OrderInfo;

import java.util.ArrayList;
import java.util.List;

class Order{

}
public class OrderGroup {
    private int Id;
    private int table;
    private List<Order> Orders = new ArrayList<>();
    public List<Order> getOrders(){
        return Orders;
    }
    public int getId(){
        return Id;
    }
    public void setId(int id){
        this.Id = id;
    }
    public void setOrders(List<Order> Orders){
        this.Orders= Orders;
    }

}
