package com.healthy.wp.Personal.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthy.wp.R;

import java.util.List;


public class PlanAdapter extends BaseAdapter {
	List<String> items;
	LayoutInflater inflater;
	Context context;
	class Viewhold{
		TextView title;
		TextView detitle;
		ImageView icon;
		ImageButton into;

	}
	public PlanAdapter(List<String> lists,Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		inflater = LayoutInflater.from(context);
		this.items=lists;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Viewhold viewhold=null;
		if(convertView==null){
			 viewhold = new Viewhold();
			convertView = inflater.inflate(R.layout.plan_item, null);
			viewhold.icon=(ImageView)convertView.findViewById(R.id.ic_con);
			viewhold.title=(TextView)convertView.findViewById(R.id.item_title);
			viewhold.detitle=(TextView)convertView.findViewById(R.id.item_tag);
			viewhold.into = (ImageButton)convertView.findViewById(R.id.into);
			convertView.setTag(viewhold);
		}
		else{
			viewhold = (Viewhold) convertView.getTag();
		}
		viewhold.icon.setImageResource(R.drawable.ic_launcher);
		viewhold.title.setText(items.get(position));
		viewhold.detitle.setText(context.getResources().getString(R.string.test_text));
		return convertView;
	}

	
}
