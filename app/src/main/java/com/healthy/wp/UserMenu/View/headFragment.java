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

import java.util.ArrayList;
import java.util.List;


public class headFragment extends Fragment implements IFTHistory {

    View v = null;
    ShowView weightview, fat_lvview, fat_view;
    HistoryPresenter presenter;
    MyApplycation myApplycation;
    List<UserMessage> husers = new ArrayList<UserMessage>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.activity_show_data, container, false);
        initView();
        return v;
    }

    private void initView() {
        myApplycation = (MyApplycation) getActivity().getApplication();
        presenter = new HistoryPresenter();
        presenter.GetHistory(myApplycation.getId(), this, getActivity());
        weightview = (ShowView) v.findViewById(R.id.v1);
        fat_lvview = (ShowView) v.findViewById(R.id.v2);
        fat_view = (ShowView) v.findViewById(R.id.v3);
        weightview.setUsers(husers);
        fat_lvview.setUsers(husers);
        fat_view.setUsers(husers);
        weightview.setTag("重量");
        fat_lvview.setTag("脂肪");
        fat_view.setTag("水份");
        weightview.setUserPerson(myApplycation.getUser());
        fat_lvview.setUserPerson(myApplycation.getUser());
        fat_view.setUserPerson(myApplycation.getUser());
    }


    @Override
    public void RefreshView(List<UserMessage> users) {

        Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
        for (int i = 0; i < users.size(); i++) {
            Log.d("Tag", "aadasd" + i + users.get(i).toString());
            this.husers.add(users.get(i));
        }
    }

    @Override
    public void success() {

    }

    @Override
    public void fail() {

    }
}
