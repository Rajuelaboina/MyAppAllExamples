package com.phycaresolutions.mymap.ordertrckingnew;

public class OrderStatus {
     String status;
     boolean isCompleted;

    public OrderStatus(String status, boolean isCompleted) {
        this.status = status;
        this.isCompleted = isCompleted;
    }

    public String getStatus() {
        return status;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}

