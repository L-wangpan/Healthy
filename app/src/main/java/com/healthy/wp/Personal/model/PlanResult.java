package com.healthy.wp.Personal.model;

import java.sql.Date;

/**
 * Created by wan_g_000 on 2016/4/4.
 */
public class PlanResult {
    private int code;
    private String title;
    private String detile;
    private String pic;
    private int num;
    private Date date;
    public PlanResult(){

    }
    public int getCode() {
        return code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetile() {
        return detile;
    }

    public void setDetile(String detile) {
        this.detile = detile;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }



    @Override
    public String toString() {
        return "PlanResult{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", detile='" + detile + '\'' +
                ", pic='" + pic + '\'' +
                ", num=" + num +
                '}';
    }
}
