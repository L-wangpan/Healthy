package com.healthy.wp.UserLogin.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.healthy.wp.MyView.CircleProgress;
import com.healthy.wp.R;
import com.healthy.wp.UserLogin.ITF.ITloginView;
import com.healthy.wp.UserLogin.model.UserMessage;
import com.healthy.wp.UserLogin.presenter.LoginPresenter;
import com.healthy.wp.UserMenu.View.MainActivity;

public class LandActivity extends Activity implements ITloginView {

    EditText userinput, passinput;
    boolean result = false;
    UserMessage userMessage;
    CircleProgress circleProgress;
    LoginPresenter loginPresenter;
    ProgressDialog progressDialog;
    View v;
    View pres;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.land);
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        v = inflater.inflate(R.layout.action, null);
        MyActionbar.setActionBar(getActionBar(), v, "登录", 2);
        initView();
    }

    private void initView() {
        userinput = (EditText) findViewById(R.id.input_id);
        passinput = (EditText) findViewById(R.id.input_pass);
        userinput.setText("admin");
        passinput.setText("admin");
        userMessage = new UserMessage();
        pres = LayoutInflater.from(getApplication()).inflate(R.layout.gressbar, null);
        circleProgress = (CircleProgress) pres.findViewById(R.id.progress);
    }

    public void onclick(View v) {

        switch (v.getId()) {
            case R.id.land:
                userMessage.setUsername(userinput.getText().toString());
                userMessage.setPassword(passinput.getText().toString());
                System.out.println(result + "asasa");
                loginPresenter = new LoginPresenter(userMessage, this, this);
                loginPresenter.login();
                break;
            case R.id.regist_user:
                startActivity(new Intent(LandActivity.this, RegistActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void showDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(pres);
        circleProgress.startAnim();
    }


    @Override
    public void StartIntentActivity(UserMessage s) {
        if (s.getCode().equals("1")) {
            dimissDialog();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", s);
            intent.putExtras(bundle);
            intent.setClass(getApplication(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplication(), "登陆失败请重新登陆！", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void dimissDialog() {
        progressDialog.dismiss();

    }

}