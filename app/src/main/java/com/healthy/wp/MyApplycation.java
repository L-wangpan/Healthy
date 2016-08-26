package com.healthy.wp;

import android.app.Application;

import com.healthy.wp.UserLogin.model.UserMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wan_g_000 on 2016/5/21.
 */
public class MyApplycation extends Application {
    String user_id;
    String password;
    String id;
    UserMessage user;
        List<UserMessage> users = new ArrayList<UserMessage>();
    public final String initValue="customer";
    public void onCreate() {
        super.onCreate();
        setUser_id(initValue);
        setPassword(initValue);
        user = new UserMessage();
    }

    public UserMessage getUser() {
        return user;
    }

    public void setUser(UserMessage user) {
        this.user = user;
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
