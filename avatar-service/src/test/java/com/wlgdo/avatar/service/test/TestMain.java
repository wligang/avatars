package com.wlgdo.avatar.service.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/11 23:47
 */

public class TestMain {

    public static void main(String args[]) {

        TestUser user = new TestUser();
        user.setName("jack");

        try {
            System.out.println("原来的:" + user.hashCode());
            TestUser user1 = (TestUser) user.clone();
            System.out.println(user1);
            try {
                TestUser user2 = (TestUser) Class.forName("com.wlgdo.avatar.service.test.TestUser").newInstance();
                System.out.println(user2);
                TestUser user3 = (TestUser) user2.clone();
                System.out.println(user3);

                TestUser user4 = (TestUser) new ObjectInputStream(new FileInputStream("C:\\Users\\acer\\git\\avatars\\avatar-service\\src\\test\\java\\com\\wlgdo\\avatar\\service\\test\\TestUser.java")).readObject();
                System.out.println(user4);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }


}
