package com.healthy.wp.Personal.View;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.healthy.wp.Personal.IT.RefreshIT;
import com.healthy.wp.Personal.model.PlanResult;
import com.healthy.wp.Personal.model.historyData;
import com.healthy.wp.Personal.presenter.PersonPresenter;
import com.healthy.wp.R;
import com.healthy.wp.UserLogin.View.MyActionbar;

import java.util.List;

public class SetUpActivity extends Activity implements RefreshIT {

    View v;
    private EditText name, born, height, weight, bmi, fat, fat_lv, lean, water, personal_des;
    TextView sex;
    ImageView change_icon;
    PersonPresenter p;
    PopupWindow popupWindow;
    historyData datas;
    LinearLayout linearLayout;
    View vwindow;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        initView();

    }

    private void initView() {
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        v = inflater.inflate(R.layout.action, null);
        sex = (TextView) findViewById(R.id.sexchange);
        name = (EditText) findViewById(R.id.choose_name);
        born = (EditText) findViewById(R.id.choose_born);
        linearLayout = (LinearLayout)findViewById(R.id.parent);
        height = (EditText) findViewById(R.id.choose_height);
        weight = (EditText) findViewById(R.id.choose_weight);
        bmi = (EditText) findViewById(R.id.choose_bmi);
        fat = (EditText) findViewById(R.id.choose_fat);
        fat_lv = (EditText) findViewById(R.id.choose_fat_lv);
        lean = (EditText) findViewById(R.id.choose_lean);
        water = (EditText) findViewById(R.id.choose_water);
        personal_des = (EditText) findViewById(R.id.jianjie);
        change_icon = (ImageView)findViewById(R.id.change_my_pic);
        vwindow = getLayoutInflater().inflate(R.layout.dialog,null);
         popupWindow = new PopupWindow(vwindow,800,300);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        setBackgroundAlpha(1);
        vwindow.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                setBackgroundAlpha(1);
            }
        });
        vwindow.findViewById(R.id.pics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                setBackgroundAlpha(1);
            }
        });
        change_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupWindow.showAtLocation(linearLayout, Gravity.CENTER,20,20);
            setBackgroundAlpha(0.7f);
            }
        });

        MyActionbar.setActionBar(getActionBar(), v, "评测数据",
                5);
        getActionBar().getCustomView().findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ;
        getActionBar().getCustomView().findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (height.getText().toString().length() == 0) {
                    Toast.makeText(getApplication(), "请填写您的身高", Toast.LENGTH_LONG).show();
                } else if (weight.getText().toString().length() == 0) {
                    Toast.makeText(getApplication(), "请填写您的体重", Toast.LENGTH_LONG).show();
                } else {
                    datas = new historyData(height.getText().toString(), weight.getText().toString());
                    p = new PersonPresenter(SetUpActivity.this);
                    p.refreshPersonal(datas, getApplication());
                }

            }
        });
    }


    @Override
    public void Error(int code) {
        Toast.makeText(getApplication(), "保存失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(int code) {
        Toast.makeText(getApplication(), "保存成功", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void RefreshUI(PlanResult ps) {

    }

    @Override
    public void RefreshMyId(List<historyData> datas) {

    }
    public void setBackgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha=bgAlpha;
        getWindow().setAttributes(lp);
    }

}
