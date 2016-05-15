package com.healthy.wp.Personal.model;

import java.sql.Date;

/**
 * Created by wan_g_000 on 2016/5/3.
 */
public class historyData {
    private String name, height, weight, bmi, fat, fat_lv, lean, water, personal_des;
    Date born;
    String userName;
    String userPassword;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public historyData(String height, String weight) {
        this.height = height;
        this.weight = weight;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getFat_lv() {
        return fat_lv;
    }

    public void setFat_lv(String fat_lv) {
        this.fat_lv = fat_lv;
    }

    public String getLean() {
        return lean;
    }

    public void setLean(String lean) {
        this.lean = lean;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getPersonal_des() {
        return personal_des;
    }

    public void setPersonal_des(String personal_des) {
        this.personal_des = personal_des;
    }
}
