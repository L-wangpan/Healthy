package com.healthy.wp.UserMenu.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.healthy.wp.MyView.ShowView;
import com.healthy.wp.R;


public class headFragment extends Fragment {

    View v=null;
    ShowView weightview,fat_lvview,fat_view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.activity_show_data, container, false);
        initView();
        return v;
    }
    private void initView(){
        weightview = (ShowView) v.findViewById(R.id.v1);
        fat_lvview = (ShowView) v.findViewById(R.id.v2);
        fat_view = (ShowView)v.findViewById(R.id.v3);
        weightview.setTag("重量");
        fat_lvview.setTag("脂肪率");
        fat_view.setTag("脂肪");
    }



}
