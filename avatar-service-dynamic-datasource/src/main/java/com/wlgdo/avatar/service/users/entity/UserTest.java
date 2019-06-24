package com.wlgdo.avatar.service.users.entity;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/24 23:49
 */
public class UserTest {
    public static void main(String args[]) {

        Set userSet = new TreeSet();
        for (long i = 0; i < 10; i++) {
            userSet.add(new User(i+"", i%3));
        }

        System.out.println(userSet);


    }
}
