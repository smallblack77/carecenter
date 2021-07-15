package org.csu.carecenter.entity;

public class OrderDiet {

    private String day;
    private String customerId;
    private String breakfastId;
    private String lunchId;
    private String dinnerId;
    private String deleteStatus;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBreakfastId() {
        return breakfastId;
    }

    public void setBreakfastId(String breakfastId) {
        this.breakfastId = breakfastId;
    }

    public String getLunchId() {
        return lunchId;
    }

    public void setLunchId(String lunchId) {
        this.lunchId = lunchId;
    }

    public String getDinnerId() {
        return dinnerId;
    }

    public void setDinnerId(String dinnerId) {
        this.dinnerId = dinnerId;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
