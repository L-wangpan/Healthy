package com.healthy.wp.UserLogin.model;

import java.io.Serializable;

/**
 * Created by wan_g_000 on 2016/3/6.
 */
public class UserMessage implements Serializable{
    private String username;
    private String password;
    private String code;
    String weight="47";
    String fat="11.06";
    String height="163";

    public UserMessage(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public UserMessage() {

    }
    public void setCode(String code){
        this.code=code;
    }
    public String getCode(){
        return this.code;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
