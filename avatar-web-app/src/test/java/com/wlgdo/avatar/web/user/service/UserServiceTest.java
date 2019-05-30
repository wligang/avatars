package com.wlgdo.avatar.web.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/29 1:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserOrderList() {

//        userService.getUserOrderList();
        userService.sortedUserList(1000000);
    }
}