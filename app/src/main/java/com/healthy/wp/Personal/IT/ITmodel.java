package com.healthy.wp.Personal.IT;

import com.healthy.wp.UserLogin.model.UserMessage;

/**
 * Created by wan_g_000 on 2016/5/3.
 */
public interface ITmodel {
     void success(String code);
     void fail(int code);
     void userUpdate(UserMessage user);
}
