package com.healthy.wp.Messure.model;

import java.sql.Date;

/**
 * Created by wan_g_000 on 2016/4/4.
 */
public class UserDetails {
    private String userId;
    private Date data;
    private double massIndex;//身体质量系数
    private double fatRate;//体脂率
    private double fatFreeMass;//去脂体重
    private double boneMass;//推定骨量
    private double moisture;//水份

    public UserDetails() {
    }

    public UserDetails(String userId, Date data, double massIndex, double fatRate, double fatFreeMass, double boneMass, double moisture) {
        this.userId = userId;
        this.data = data;
        this.massIndex = massIndex;
        this.fatRate = fatRate;
        this.fatFreeMass = fatFreeMass;
        this.boneMass = boneMass;
        this.moisture = moisture;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getMassIndex() {
        return massIndex;
    }

    public void setMassIndex(double massIndex) {
        this.massIndex = massIndex;
    }

    public double getFatRate() {
        return fatRate;
    }

    public void setFatRate(double fatRate) {
        this.fatRate = fatRate;
    }

    public double getFatFreeMass() {
        return fatFreeMass;
    }

    public void setFatFreeMass(double fatFreeMass) {
        this.fatFreeMass = fatFreeMass;
    }

    public double getBoneMass() {
        return boneMass;
    }

    public void setBoneMass(double boneMass) {
        this.boneMass = boneMass;
    }

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }
}
