package com.wlgdo.avatar.service.users.entity;

import lombok.Data;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
public class User implements Serializable, Comparable<User> {

    private Integer id;

    private String name;

    private String password;

    private String accountNo;

    private Date updateTime;

    private Long rank;

    private User() {

    }

    public User(String name,Long rank) {
        this.name=name;
        this.rank = rank;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank=" + rank +
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


    @Override
    public int compareTo(User o) {
        return this.rank - o.getRank() > -1 ? 1 : -1;
    }
}
