package com.healthy.wp.Personal.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.healthy.wp.Personal.IT.RefreshIT;
import com.healthy.wp.Personal.model.PlanResult;
import com.healthy.wp.Personal.model.historyData;
import com.healthy.wp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanActivity extends Activity implements RefreshIT{
	ImageButton back;
	RadioButton mysport,myeat;
	ListView itemsview;
	PlanAdapter adapter;
	List<String> str=new ArrayList<String>();
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			adapter.notifyDataSetChanged();
		};
	};
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.plan_activity);
		initView();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		mysport = (RadioButton)findViewById(R.id.my_sport);
		myeat=(RadioButton)findViewById(R.id.my_eat);
		itemsview = (ListView)findViewById(R.id.plan_listview);
		Intent intent = getIntent();
		String flag= intent.getStringExtra("flag");
		str.clear();
		if(flag.equals("2")){
			str.addAll(Arrays.asList(getResources().getStringArray(R.array.sports)));
			adapter = new PlanAdapter(str, this);
		}
		else{
			str.addAll(Arrays.asList(getResources().getStringArray(R.array.eat)));
			adapter = new PlanAdapter(str, this);
		}
		
		
		itemsview.setAdapter(adapter);
	}

	public void onClick(View v){
		switch (v.getId()) {
		case R.id.qiut:
			finish();
			break;
		case R.id.my_sport:
			mysport.setChecked(true);
			List<String> l1= Arrays.asList(getResources().getStringArray(R.array.sports));
			str.clear();
			str.addAll(l1);
			handler.sendMessage(new Message());
			break;
		case R.id.my_eat:
			myeat.setChecked(true);
			List<String> l2= Arrays.asList(getResources().getStringArray(R.array.eat));
			str.clear();
			str.addAll(l2);
			handler.sendMessage(new Message());
			break;
		default:
			break;
		}
	}

	@Override
	public void Error(int code) {

	}

	public void success(String path) {

	}

	@Override
	public void RefreshUI(PlanResult ps) {

	}

	@Override
	public void RefreshMyId(List<historyData> datas) {

	}

	class Check implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
