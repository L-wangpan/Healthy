package com.healthy.wp;

import android.app.Application;

/**
 * Created by wan_g_000 on 2016/5/21.
 */
public class MyApplycation extends Application {
    String user_id;
    String password;
    String id;
    public final String initValue="customer";
    public void onCreate() {
        super.onCreate();
        setUser_id(initValue);
        setPassword(initValue);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInitValue() {
        return initValue;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
