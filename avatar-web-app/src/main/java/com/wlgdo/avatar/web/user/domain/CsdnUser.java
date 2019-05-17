package com.wlgdo.avatar.web.user.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/4 19:11
 */
public class CsdnUser {

    private String grade;

    private int fansNum;

    private Date updateTime;

    private String avatarUrl;

    private String nickname;

    private int ranking;

    private String userName;

    private int integration;

    private int interview;

    public int getInterview() {
        return interview;
    }

    public void setInterview(int interview) {
        this.interview = interview;
    }

    public List<CsdnArticles> getArticles() {
        return articles;
    }

    public void setArticles(List<CsdnArticles> articles) {
        this.articles = articles;
    }

    List<CsdnArticles> articles;

    public CsdnUser() {
    }

    public CsdnUser(String userName, String nickname) {
        this.userName = userName;
        this.nickname = nickname;
        this.avatarUrl = "http://img.wlgdo.com/avatar/no-body.png";
        this.ranking = 0;
        this.integration = 0;
        this.fansNum = 0;
        this.grade = "0级";
        this.integration = 0;
        updateTime = new Date();
        articles = new ArrayList<>();

    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }
}
