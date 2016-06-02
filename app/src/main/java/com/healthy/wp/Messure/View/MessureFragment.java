package com.healthy.wp.Messure.View;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.healthy.wp.Messure.IT.ITUI;
import com.healthy.wp.Messure.model.UserDetails;
import com.healthy.wp.Messure.presenter.MessurePresenter;
import com.healthy.wp.MyApplycation;
import com.healthy.wp.R;
import com.healthy.wp.UserLogin.model.UserMessage;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MessureFragment extends Fragment implements ITUI{

	View view = null;
	public static MyView myView;
	Button bt;
	MyApplycation myApplycation;
	public static UserMessage userMessage;
	MessurePresenter messurePresenter;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.messure, null);
		myApplycation = (MyApplycation)getActivity().getApplication();
		initView();

		return view;
	}
	Button fatRate,moisture,fatFreeMass,userage,massIndex;
	private void initView(){
		fatRate = (Button)view.findViewById(R.id.fat_bgg);
		moisture = (Button)view.findViewById(R.id.water_bg);
		fatFreeMass = (Button)view.findViewById(R.id.lean_fat_bg);
		userage=(Button)view.findViewById(R.id.age_bg);
		massIndex = (Button)view.findViewById(R.id.bmi_bg);
		myView = new MyView(getActivity());
		myView = (MyView)view.findViewById(R.id.myview);
		userMessage = myApplycation.getUser();
		Log.d("Tag","ppppppp"+userMessage);
		myView.drawData(userMessage);
		fatRate.setText(userMessage.getFatRate());
		moisture.setText(userMessage.getMoisture());
		fatFreeMass.setText(userMessage.getFatFreeMass());
//		String []year = myApplycation.getUser().getDate().split("-");
//		Date d = new Date();
		userage.setText(userMessage.getAge());
		massIndex.setText(userMessage.getMassIndex());

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

			case R.id.fat_bgg:
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
	}
}
