package com.wlgdo.avatar.service.test;

import lombok.Data;

import java.io.Serializable;


@Data
public class TestUser implements Cloneable,Serializable {
    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        TestUser testUser = null;
        testUser = (TestUser) super.clone();
        return testUser;
    }

    @Override
    public String toString() {
        return this.name + this.hashCode();
    }
}