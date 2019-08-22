package com.wlgdo.avatar.web.apps.domain;



/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/3 23:30
 */

public class AppInfo {
    public String appKey;
    String appName;
    String appId;
    String secretKey;

    public AppInfo() {

    }

    public AppInfo(String appName) {
        this.appName = appName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "appKey='" + appKey + '\'' +
                ", appName='" + appName + '\'' +
                ", appId='" + appId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
