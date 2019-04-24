package com.wlgdo.avatar.service.model;

import java.io.Serializable;


public class User implements Serializable {

    private String name;
    private String accessNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessNo() {
        return accessNo;
    }

    public void setAccessNo(String accessNo) {
        this.accessNo = accessNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", accessNo='" + accessNo + '\'' +
                '}';
    }
}
