package org.csu.carecenter.entity;

import lombok.Data;

@Data
public class Customer {
    private int id;

    private String name;

    private String sex;

    private String phone;

    private int age;

    private Double height;

    private Double weight;

    private String birthday;

    private String attention;

    private String rfid;
}
