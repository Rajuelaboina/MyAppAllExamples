package com.phycaresolutions.mymap.ordertrckingnew;

public class TrackingStep {
    private String status;
    private boolean isCompleted;

    public TrackingStep(String status, boolean isCompleted) {
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
