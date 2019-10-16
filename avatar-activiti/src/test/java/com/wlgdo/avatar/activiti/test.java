package com.wlgdo.avatar.activiti;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/4/30 15:32
 */
public class test {
    public static void main(String args[]) {
        try {
            System.out.println("start");
            String[] args1 = new String[]{"python", "C://Users//acer//git//csdn//articles//csdn.py", "zhangzhenzuo"};
            Process pr = Runtime.getRuntime().exec(args1);

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(),"utf8"));
            String line;
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
