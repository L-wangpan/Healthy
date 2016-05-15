package com.healthy.wp.UserLogin.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.healthy.wp.Personal.IT.RefreshIT;
import com.healthy.wp.Personal.model.PlanResult;
import com.healthy.wp.Personal.model.historyData;
import com.healthy.wp.R;
import com.healthy.wp.UserLogin.model.UserMessage;
import com.healthy.wp.UserLogin.presenter.LoginPresenter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RegistActivity extends Activity implements RefreshIT {
    Button regist, haveuser;
    EditText phone, phone_pass;
    View v;
    LoginPresenter loginPresenter;
    UserMessage user = new UserMessage();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        v = inflater.inflate(R.layout.action, null);
        MyActionbar.setActionBar(getActionBar(), v, "注册",
                1);
        initView();

    }

    public void initView() {
        phone = (EditText) findViewById(R.id.regist_input_id);
        phone_pass = (EditText) findViewById(R.id.regist_input_pass);
        regist = (Button) findViewById(R.id.regist_bg);
        haveuser = (Button) findViewById(R.id.has_user);
        haveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplication(), "手机号不能为空。", Toast.LENGTH_LONG).show();
                } else if (phone_pass.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplication(), "密码不能为空。", Toast.LENGTH_LONG).show();
                } else {
                    user.setUsername(phone.getText().toString());
                    user.setPassword(phone_pass.getText().toString());
                    loginPresenter = new LoginPresenter(user, getApplication(), RegistActivity.this);
                    loginPresenter.regist();
                }
            }
        });
    }

    @Override
    public void Error(int code) {
        Toast.makeText(getApplication(), "注册失败,该手机号已被注册！", Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(int code) {
        Timer t  = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        };
        t.schedule(task,3);
        Toast.makeText(getApplication(), "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void RefreshUI(PlanResult ps) {

    }

    @Override
    public void RefreshMyId(List<historyData> datas) {

    }
}
