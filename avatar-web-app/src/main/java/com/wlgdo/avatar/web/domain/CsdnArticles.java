package com.wlgdo.avatar.web.domain;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/4 20:39
 */
public class CsdnArticles {

    String href;
    String title;
    int interview;
    int moment;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInterview() {
        return interview;
    }

    public void setInterview(int interview) {
        this.interview = interview;
    }

    public int getMoment() {
        return moment;
    }

    public void setMoment(int moment) {
        this.moment = moment;
    }

    @Override
    public String toString() {
        return "CsdnArticles{" +
                "href='" + href + '\'' +
                ", title='" + title + '\'' +
                ", interview=" + interview +
                ", moment=" + moment +
                '}';
    }
}
