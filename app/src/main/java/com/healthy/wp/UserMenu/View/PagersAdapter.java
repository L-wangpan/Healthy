package com.healthy.wp.UserMenu.View;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

public class PagersAdapter extends PagerAdapter {
	List<ImageView> images;

	public PagersAdapter(List<ImageView> images) {
		// TODO Auto-generated constructor stub
		this.images=images;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
    @Override    
    public void destroyItem(ViewGroup container, int position,    
            Object object) {    
    	
    }
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub

		
		ImageView view = images.get(position) ;
		ViewParent vp =  view.getParent();
		if(vp != null){
			ViewGroup parent = (ViewGroup)vp;
			parent.removeView(view);
		}
		((ViewPager)container).addView(images.get(position));
		
		return images.get(position);
	}
}
