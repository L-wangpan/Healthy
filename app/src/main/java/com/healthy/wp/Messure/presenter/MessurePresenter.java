package com.healthy.wp.Messure.presenter;

import android.content.Context;
import android.os.Handler;

import com.healthy.wp.Messure.IT.ITUI;
import com.healthy.wp.Messure.IT.LoadData;
import com.healthy.wp.Messure.model.MessureModel;
import com.healthy.wp.Messure.model.UserDetails;
import com.healthy.wp.UserLogin.model.UserMessage;

/**
 * Created by wan_g_000 on 2016/4/4.
 */
public class MessurePresenter {
    ITUI callback;
    Context context;
    MessureModel messureModel;
    UserMessage user;
    Handler handler = new Handler();
   public MessurePresenter(Context context,ITUI callback,UserMessage user){
       this.context=context;
       this.callback=callback;
        messureModel = new MessureModel();
       this.user=user;
   }
   public void RequestData(){
                messureModel.getPersonalDels(user, new LoadData() {
                    @Override
                    public void OnSuccess(final UserDetails userDetails) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.RefreshView(userDetails);
                            }
                        });
                    }

                    @Override
                    public void Fail(int code) {
                                callback.Fail();
                    }
                });
   }

}
