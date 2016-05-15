package com.healthy.wp.UserLogin.ITF;

import com.healthy.wp.UserLogin.model.UserMessage;

/**
 * Created by wan_g_000 on 2016/3/15.
 */
public interface ITloginView {
    void StartIntentActivity(UserMessage s);
    void dimissDialog();
    void showDialog();
}
