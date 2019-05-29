package com.wlgdo.avatar.web.user.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/4 19:11
 */

public class CsdnUser implements Serializable {
    private static final long serialVersionUID=-9166068087180141499L;

    public CsdnUser() {

    }

    public CsdnUser(String nickname) {
        this.nickname = nickname;
        this.updateTime=new Date();
        this.ranking=System.currentTimeMillis();

    }

    public CsdnUser(String userName, String nickname) {
        this.userName = userName;
        this.nickname = nickname;
        this.avatarUrl = "http://img.wlgdo.com/avatar/no-body.png";
        this.ranking = 0l;
        this.integration = 0;
        this.fansNum = 0;
        this.grade = "0级";
        this.integration = 0;
        updateTime = new Date();
        articles = new ArrayList<>();

    }

    private String grade;

    private int fansNum;

    private Date updateTime;

    private String avatarUrl;

    private String nickname;

    private Long ranking;

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

    public Long getRanking() {
        return ranking;
    }

    public void setRanking(Long ranking) {
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

    @Override
    public String toString() {
        return "CsdnUser{" +
                "grade='" + grade + '\'' +
                ", fansNum=" + fansNum +
                ", updateTime=" + updateTime +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", ranking=" + ranking +
                ", userName='" + userName + '\'' +
                ", integration=" + integration +
                ", interview=" + interview +
                ", articles=" + articles +
                '}';
    }
}
