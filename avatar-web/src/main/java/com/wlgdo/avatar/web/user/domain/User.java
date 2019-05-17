package com.wlgdo.avatar.web.user.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/17 22:30
 */
@Data
@ToString
public class User implements Serializable {

    private Integer id;

    private String name;

    private String password;

    private String accountNo;

    private Date updateTime;

}