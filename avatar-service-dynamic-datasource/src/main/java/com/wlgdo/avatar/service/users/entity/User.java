package com.wlgdo.avatar.service.users.entity;

import lombok.Data;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private String password;

    private String accountNo;

    private Date updateTime;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User user = (User) object;
        return (user.getName().equals(this.name) && user.getAccountNo().equals(this.accountNo));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getAccountNo());
    }
}
