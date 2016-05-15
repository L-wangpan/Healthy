package com.healthy.wp.Messure.View;

import android.annotation.TargetApi;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.healthy.wp.Messure.IT.ITUI;
import com.healthy.wp.Messure.model.UserDetails;
import com.healthy.wp.Messure.presenter.MessurePresenter;
import com.healthy.wp.MyView.ShowDataActivity;
import com.healthy.wp.R;
import com.healthy.wp.UserLogin.model.UserMessage;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MessureFragment extends Fragment implements ITUI{

	View view = null;
	MyView myView;
	Button bt;
	MessurePresenter messurePresenter;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.messure, null);
		initView();
		return view;
	}
	private void initView(){
		myView = new MyView(getActivity());
		myView = (MyView)view.findViewById(R.id.myview);
		bt=(Button)view.findViewById(R.id.fat_bg);
		UserMessage userMessage = new UserMessage();
		userMessage.setFat("22.03");
		userMessage.setHeight("175");
		userMessage.setWeight("60");
		myView.drawData(userMessage);
		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startAnotherActivity();
			}
		});
	}

	@Override
	public void RefreshView(UserDetails userDetails) {

	}

	@Override
	public void Fail() {
		Toast.makeText(getActivity(),"获取数据失败。。。",Toast.LENGTH_LONG).show();
	}

	public void onClick(View view) {
		switch (view.getId())
		{
			case R.id.fat_bg:
				startAnotherActivity();
				break;
			case R.id.water_bg:
				startAnotherActivity();
				break;
			case R.id.lean_fat_bg:
				startAnotherActivity();
				break;
			case R.id.age_bg:
				startAnotherActivity();
				break;
			case R.id.bmi_bg:
				startAnotherActivity();
				break;
			default:
				break;
		}
	}
	public void startAnotherActivity(){
			startActivity(new Intent().setClass(getActivity(), ShowDataActivity.class));
	}
}
