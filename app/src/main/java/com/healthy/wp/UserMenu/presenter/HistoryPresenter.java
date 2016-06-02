package com.healthy.wp.UserMenu.presenter;

import android.content.Context;

import com.healthy.wp.UserLogin.model.UserMessage;
import com.healthy.wp.UserMenu.ITF.CallbackHistory;
import com.healthy.wp.UserMenu.ITF.IFTHistory;
import com.healthy.wp.UserMenu.model.HistoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pan on 2016/6/1.
 */
public class HistoryPresenter {
    public void GetHistory(String userid, final IFTHistory callbackui, Context context) {
        new HistoryModel().getHistory(userid, new CallbackHistory() {
            @Override
            public void success() {
                callbackui.success();
            }

            @Override
            public void fail() {
                callbackui.fail();
            }

            @Override
            public void RefreshData(List<UserMessage> users) {
                List<UserMessage> userMessages = new ArrayList<UserMessage>();
                for (int i=0;i<users.size();i++)
                    userMessages.add(users.get(i));
                callbackui.RefreshView(userMessages);
            }
        },context);
    }

}
