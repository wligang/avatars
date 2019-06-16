package com.wlgdo.avatar.web.common.enums;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/17 0:49
 */
public enum ELockKeyNameSpace {

    HIDO_APP("wlgdo_hido_app_");

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    ELockKeyNameSpace(String info) {
        this.info = info;
    }

}
