package org.csu.carecenter.entity;

public class CustomerAndUser {

    private int id;
    private String custId;
    private String docId;
    private String nurId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getNurId() {
        return nurId;
    }

    public void setNurId(String nurId) {
        this.nurId = nurId;
    }
}
