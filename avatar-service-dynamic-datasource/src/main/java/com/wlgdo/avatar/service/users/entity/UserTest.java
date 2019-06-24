package com.wlgdo.avatar.service.users.entity;

import java.util.Set;
import java.util.TreeSet;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/24 23:49
 */
public class UserTest {
    public static void main(String args[]) {
        User user = new User(1L);

        Set userSet = new TreeSet();
        for (long i = 0; i < 100; i++) {
            userSet.add(new User(i));
        }

        System.out.println(userSet);


    }
}
