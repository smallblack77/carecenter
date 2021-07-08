package org.csu.carecenter.entity;

public class Out {
    private int id;
    private int customerId;
    private String reason;
    private String startTime;
    private String expectReturnTime;
    private String actualReturnTime;
    private String airPhone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getExpectReturnTime() {
        return expectReturnTime;
    }

    public void setExpectReturnTime(String expectReturnTime) {
        this.expectReturnTime = expectReturnTime;
    }

    public String getActualReturnTime() {
        return actualReturnTime;
    }

    public void setActualReturnTime(String actualReturnTime) {
        this.actualReturnTime = actualReturnTime;
    }

    public String getAirPhone() {
        return airPhone;
    }

    public void setAirPhone(String airPhone) {
        this.airPhone = airPhone;
    }
}
