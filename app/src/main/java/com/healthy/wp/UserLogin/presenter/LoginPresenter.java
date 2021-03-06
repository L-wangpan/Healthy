package com.healthy.wp.UserLogin.presenter;

import android.content.Context;
import android.os.Handler;

import com.healthy.wp.Personal.IT.RefreshIT;
import com.healthy.wp.UserLogin.ITF.ITloginView;
import com.healthy.wp.UserLogin.ITF.LoginCallBack;
import com.healthy.wp.UserLogin.model.LoginModel;
import com.healthy.wp.UserLogin.model.UserMessage;

/**
 * Created by wan_g_000 on 2016/3/14.
 */
public class LoginPresenter {
    UserMessage user;
    Context context;
    ITloginView callBack;
    LoginModel loginModel;
    RefreshIT refreshIT;
    Handler handler = new Handler();

    public LoginPresenter(UserMessage user, Context context, ITloginView callBack) {
        this.user = user;
        this.context = context;
        this.callBack = callBack;
        loginModel = new LoginModel(context);
    }
    public LoginPresenter(UserMessage user, Context context, RefreshIT mrefreshIT) {
        this.user = user;
        this.context = context;
        this.refreshIT = mrefreshIT;
        loginModel = new LoginModel(context);
    }

    public void login() {
//        Toast.makeText(context,"asasa",Toast.LENGTH_LONG).show();
        callBack.showDialog();
        loginModel.Login(user, new LoginCallBack() {
            @Override
            public void Success(UserMessage s) {
                System.out.println("12314");
                callBack.dimissDialog();
                final UserMessage userMessage = new UserMessage();
                userMessage.setCode(s.getCode());
                userMessage.setId(s.getId());
                userMessage.setHeight(s.getHeight());
                userMessage.setWeight(s.getWeight());
                userMessage.setDate(s.getDate());
                userMessage.setNickname(s.getNickname());
                userMessage.setUsername(s.getUsername());
                userMessage.setFatFreeMass(s.getFatFreeMass());
                userMessage.setFat(s.getFat());
                userMessage.setBoneMass(s.getBoneMass());
                userMessage.setMassIndex(s.getMassIndex());
                userMessage.setFatRate(s.getFatRate());
                userMessage.setMoisture(s.getMoisture());
                userMessage.setNickname(s.getNickname());
                userMessage.setHeadPortrait(s.getHeadPortrait());
                userMessage.setSex(s.getSex());
                userMessage.setAge(s.getAge());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.update(userMessage);
                        callBack.StartIntentActivity(userMessage);
                    }
                });
            }

            @Override
            public void Onfial() {
                callBack.dimissDialog();
            }
        });
    }

    public void regist() {
        loginModel.regist(user, new LoginCallBack() {
            @Override
            public void Success(UserMessage s) {
                refreshIT.success(s.getCode());
            }

            @Override
            public void Onfial() {

            }
        });
    }


}
