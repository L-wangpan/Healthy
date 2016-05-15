package com.healthy.wp.UserLogin.View;


import android.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.healthy.wp.R;

public class MyActionbar {
	static TextView titletext;
	private static ImageButton left;
	private static ImageButton right;
	public static void setActionBar(ActionBar actionBar, View view,
									String title, int flag) {
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowCustomEnabled(true);
		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL);
		actionBar.setCustomView(view, lp);
//		actionBar.setTitle(title);
		right = (ImageButton)actionBar.getCustomView().findViewById(R.id.right);
		left = (ImageButton)actionBar.getCustomView().findViewById(R.id.left);
		titletext = (TextView)actionBar.getCustomView().findViewById(R.id.title);
		titletext.setText(title);

		if(flag==2){
			right.setVisibility(View.INVISIBLE);
			left.setVisibility(View.INVISIBLE);
		}
		else if(flag==3){
			left.setVisibility(View.VISIBLE);
//			left.setVisibility(View.INVISIBLE);
			right.setVisibility(View.INVISIBLE);
		}

		else  if(flag==4){
			right.setImageResource(R.drawable.setup);
			right.setVisibility(View.VISIBLE);
		}
		else if(flag==5){
			left.setVisibility(View.VISIBLE);
			right.setImageResource(R.drawable.save);
			right.setVisibility(View.VISIBLE);
		}
		else if(flag==6){
			left.setVisibility(View.INVISIBLE);
			right.setImageResource(R.drawable.camera);
			right.setVisibility(View.VISIBLE);
		}
		else{
			right.setVisibility(View.INVISIBLE);
			left.setVisibility(View.INVISIBLE);
		}

	}
}
