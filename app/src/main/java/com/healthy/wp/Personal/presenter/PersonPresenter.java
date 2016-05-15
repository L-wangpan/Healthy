package com.healthy.wp.Personal.presenter;

import android.content.Context;

import com.healthy.wp.Personal.IT.ITmodel;
import com.healthy.wp.Personal.IT.RefreshIT;
import com.healthy.wp.Personal.model.PersonalModel;
import com.healthy.wp.Personal.model.historyData;

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
                public void success(int code) {
                    callback.success(code);

                }

                @Override
                public void fail(int code) {
                    callback.Error(code);
                }
            },context,"",person);
    }

}
