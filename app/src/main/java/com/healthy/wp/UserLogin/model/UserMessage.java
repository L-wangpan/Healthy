package com.healthy.wp.UserLogin.model;

import java.io.Serializable;

/**
 * Created by wan_g_000 on 2016/3/6.
 */
public class UserMessage implements Serializable{
    private String username;
    private String password;
    private String code;
    String weight="0.000";
    String sex;
    String id;
    String fat="0.000";
    String height="0.000";
    String Nickname;
    String date;
    String massIndex="0.0000";
    String age="0";
    String fatRate="0.0000";
    String fatFreeMass="0.0000";
    String boneMass="0.0000";
    String headPortrait;
    String moisture="0.0000";
    public UserMessage(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getMoisture() {
        return moisture;
    }

    public void setMoisture(String moisture) {
        this.moisture = moisture;
    }

    public String getBoneMass() {
        return boneMass;
    }

    public void setBoneMass(String boneMass) {
        this.boneMass = boneMass;
    }

    public String getMassIndex() {
        return massIndex;
    }

    public void setMassIndex(String massIndex) {
        this.massIndex = massIndex;
    }

    public String getFatRate() {
        return fatRate;
    }

    public void setFatRate(String fatRate) {
        this.fatRate = fatRate;
    }

    public String getFatFreeMass() {
        return fatFreeMass;
    }

    public void setFatFreeMass(String fatFreeMass) {
        this.fatFreeMass = fatFreeMass;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String pickname) {
        this.Nickname = pickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "UserMessage{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", weight='" + weight + '\'' +
                ", sex='" + sex + '\'' +
                ", id='" + id + '\'' +
                ", fat='" + fat + '\'' +
                ", height='" + height + '\'' +
                ", Nickname='" + Nickname + '\'' +
                ", date='" + date + '\'' +
                ", massIndex='" + massIndex + '\'' +
                ", fatRate='" + fatRate + '\'' +
                ", fatFreeMass='" + fatFreeMass + '\'' +
                ", boneMass='" + boneMass + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", moisture='" + moisture + '\'' +
                '}';
    }
}
