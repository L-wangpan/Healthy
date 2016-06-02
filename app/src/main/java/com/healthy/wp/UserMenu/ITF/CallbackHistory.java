package com.healthy.wp.UserMenu.ITF;

import com.healthy.wp.UserLogin.model.UserMessage;

import java.util.List;

/**
 * Created by pan on 2016/6/1.
 */
public interface CallbackHistory
{
    void success();
    void fail();
    void RefreshData(List<UserMessage> users);
}
