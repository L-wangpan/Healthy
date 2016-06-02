package com.healthy.wp.Personal.presenter;

import android.content.Context;

import com.healthy.wp.Personal.IT.ITmodel;
import com.healthy.wp.Personal.IT.RefreshIT;
import com.healthy.wp.Personal.model.PersonalModel;
import com.healthy.wp.Personal.model.historyData;
import com.healthy.wp.UserLogin.model.UserMessage;

/**
 * Created by wan_g_000 on 2016/4/4.
 */
public class PersonPresenter {
    RefreshIT callback;
    public PersonPresenter(RefreshIT refreshIT){
        this.callback=refreshIT;
    }
    public void refreshPersonal(historyData person, final Context context){
            new PersonalModel().refreshPesonal(new ITmodel() {
                @Override
                public void success(String path) {
                    callback.success(path);
                }
                @Override
                public void fail(int code)
                {
                    callback.Error(code);
                }

                @Override
                public void userUpdate(UserMessage user) {
                    callback.RefreshIdentify(user);
                }
            },context,person);
    }

}
