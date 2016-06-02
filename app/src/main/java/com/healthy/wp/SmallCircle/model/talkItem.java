package com.healthy.wp.SmallCircle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wan_g_000 on 2016/5/29.
 */
public class talkItem {
    String dynamicInformation;
    String dynamicDate;
    List<String> urls = new ArrayList<String>();
    List<Talk> talks = new ArrayList<Talk>();

    public talkItem() {
    }

    public talkItem(String dynamicInformation, String dynamicDate, List<String> urls, List<Talk> talks) {
        this.dynamicInformation = dynamicInformation;
        this.dynamicDate = dynamicDate;
        this.urls = urls;
        this.talks = talks;
    }

    public void addURL(String u) {
        urls.add(u);
    }

    public String getDynamicInformation() {
        return dynamicInformation;
    }

    public void setDynamicInformation(String dynamicInformation) {
        this.dynamicInformation = dynamicInformation;
    }

    public String getDynamicDate() {
        return dynamicDate;
    }

    public void setDynamicDate(String dynamicDate) {
        this.dynamicDate = dynamicDate;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

    @Override
    public String toString() {
        return "talkItem{" +
                "dynamicInformation='" + dynamicInformation + '\'' +
                ", dynamicDate='" + dynamicDate + '\'' +
                ", urls=" + urls +
                ", talks=" + talks +
                '}';
    }
}
