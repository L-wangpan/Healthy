package com.healthy.wp.UserMenu.ITF;

import com.healthy.wp.UserLogin.model.UserMessage;

import java.util.List;

/**
 * Created by wan_g_000 on 2016/3/22.
 */
public interface IFTHistory {
    void RefreshView(List<UserMessage> users);
    void success();
    void fail();
}
