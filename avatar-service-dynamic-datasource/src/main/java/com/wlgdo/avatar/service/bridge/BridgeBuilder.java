package com.wlgdo.avatar.service.bridge;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/12 23:36
 */
public class BridgeBuilder {

    @Autowired
    private UserInterface userInterface;

    public Object save(Object o) {
        userInterface.save(o);
        return o;
    }

}
