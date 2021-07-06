package org.csu.carecenter.entity;

public class Bed {
    private Integer id;

    private String roomNum;

    private boolean bedStatus;

    private String sort;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public boolean isBedStatus() {
        return bedStatus;
    }

    public void setBedStatus(boolean bedStatus) {
        this.bedStatus = bedStatus;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
