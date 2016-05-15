package com.healthy.wp.UserMenu.model;

/**
 * Created by wan_g_000 on 2016/3/6.
 */
public class UserMessage {
    private String userName;
    private String userPassword;
    private String headPortrait;//头像
    private String sex;
    private double weight;
    private double height;

    private java.sql.Date date;
    public UserMessage(String username, String password) {
        this.userName=username;
        this.userPassword=password;
    }

    public UserMessage(String userName, String userPassword, String headPortrait, String sex, double weight, double height, java.sql.Date date) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.headPortrait = headPortrait;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public UserMessage() {

    }

    public String getPassword() {
        return userPassword;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getUsername() {
        return userName;
    }

    public void setPassword(String password) {
        this.userPassword = password;
    }
}
