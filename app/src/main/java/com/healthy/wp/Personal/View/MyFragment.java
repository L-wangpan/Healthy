package com.healthy.wp.Personal.View;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.healthy.wp.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyFragment extends Fragment{
	
	View view=null;
	Button eat_bt,sport_bg;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.my, null);
		initView();
		return view;
	}
	public void initView(){
		eat_bt = (Button)view.findViewById(R.id.eat_page);
		sport_bg = (Button)view.findViewById(R.id.sport_page);
		eat_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getActivity(), PlanActivity.class);
				intent.putExtra("flag", "1");
				startActivity(intent);
				
			}
		});
		sport_bg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "ok", Toast.LENGTH_LONG).show();
				Intent intent = new Intent();
				intent.setClass(getActivity(), PlanActivity.class);
				intent.putExtra("flag", "2");
				startActivity(intent);
			}
		});


	}
	/*public void onClick(View v){
		switch (v.getId()) {
		case R.id.eat_page:
			Toast.makeText(getActivity(), "ok", 3).show();
			startActivity(new Intent().setClass(getActivity(), PlanActivity.class));
			break;
		case R.id.sport_page:
			startActivity(new Intent().setClass(getActivity(), PlanActivity.class));
			break;

		default:
			break;
		}
	}*/
}
