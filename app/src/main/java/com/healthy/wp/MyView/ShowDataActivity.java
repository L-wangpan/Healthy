package com.healthy.wp.MyView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.healthy.wp.R;
import com.healthy.wp.UserLogin.View.MyActionbar;

public class ShowDataActivity extends Activity {

View v;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_data);
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        v = inflater.inflate(R.layout.action,null);
        MyActionbar.setActionBar(getActionBar(), v, "评测数据",
                3);
        getActionBar().getCustomView().findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
