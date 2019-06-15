package com.wlgdo.avatar.service.users.controller;


import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/15 23:57
 */
public class BeanMapper<T> {

    public static Object map(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static List<?> mapList(Collection source, Class<?> target) {

        List<Object> targetList = new ArrayList(source.size());

        source.stream().forEach(e -> {
            try {
                targetList.add(map(e, target.newInstance()));
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        });

        return targetList;
    }

}
