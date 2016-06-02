package com.healthy.wp.UserMenu.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.healthy.wp.MyApplycation;
import com.healthy.wp.MyView.ShowView;
import com.healthy.wp.R;
import com.healthy.wp.UserLogin.model.UserMessage;
import com.healthy.wp.UserMenu.ITF.IFTHistory;
import com.healthy.wp.UserMenu.presenter.HistoryPresenter;

import java.util.List;


public class headFragment extends Fragment implements IFTHistory {

    View v = null;
    ShowView weightview, fat_lvview, fat_view;
    HistoryPresenter presenter;
    MyApplycation myApplycation;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.activity_show_data, container, false);
        initView();
        return v;
    }

    private void initView() {
        weightview = (ShowView) v.findViewById(R.id.v1);
        fat_lvview = (ShowView) v.findViewById(R.id.v2);
        fat_view = (ShowView) v.findViewById(R.id.v3);
        myApplycation = (MyApplycation) getActivity().getApplication();
        presenter = new HistoryPresenter();
        presenter.GetHistory(myApplycation.getId(), this, getActivity());
        weightview.setTag("重量");
        fat_lvview.setTag("脂肪率");
        fat_view.setTag("水份");
    }


    @Override
    public void RefreshView(List<UserMessage> users) {
        Toast.makeText(getActivity(),"成功",Toast.LENGTH_LONG).show();
       for (int i=0;i<users.size();i++)
        Log.d("Tag",i+users.get(i).toString());
        weightview.setUser(users);
        fat_lvview.setUser(users);
        fat_view.setUser(users);
        weightview.invalidate();
    }

    @Override
    public void success() {

    }

    @Override
    public void fail() {

    }
}
