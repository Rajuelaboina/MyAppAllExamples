package com.phycaresolutions.mymap.odertracking;

public class OrderStep {
    private String status;
    private String timestamp;
    private boolean isCompleted;

    public OrderStep(String status, String timestamp, boolean isCompleted) {
        this.status = status;
        this.timestamp = timestamp;
        this.isCompleted = isCompleted;
    }

    public String getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}

