package com.healthy.wp.UserMenu.View;

/**
 * Created by wan_g_000 on 2016/4/1.
 */
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.healthy.wp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressLint({ "HandlerLeak", "ShowToast" })
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FistPage extends Fragment {

    View view=null;
    LinearLayout layoutdot;
    boolean isAuto = true;
    MyViewPager mviewPager;
    int currentitem = 0;
    List<ImageView> dotimages;
    List<ImageView> pagersimages;
    PagersAdapter adapter;
    LayoutInflater inflater;
    ScheduledExecutorService scheduledExecutorService;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 100) {
                mviewPager.setCurrentItem(currentitem);
                System.out.println("ok");
            }
        }

    };


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.home_page, null);
        mviewPager = (MyViewPager) view.findViewById(R.id.viewpager);
        layoutdot = (LinearLayout) view.findViewById(R.id.layout_dot);
        inflater = LayoutInflater.from(getActivity());
        layoutdot.removeAllViews();
        initView();


        mviewPager.setAdapter(adapter);
        mviewPager.setCurrentItem(0);
        mviewPager.setOnPageChangeListener(new ViewPagerListenner());

        if (isAuto) {
            startFilpping();
        }
        return view;
    }

    private void initView() {
        dotimages = new ArrayList<ImageView>();
        pagersimages = new ArrayList<ImageView>();
        for (int i = 0; i < 4; i++) {
            ImageView image = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    new LayoutParams(LayoutParams.WRAP_CONTENT,
                            android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

            params.leftMargin = 15;
            if (i == 0) {
                image.setBackgroundResource(R.drawable.point_pressed);
            } else {
                image.setBackgroundResource(R.drawable.point_unpressed);
            }
            layoutdot.addView(image, params);
            dotimages.add(image);
        }

        ImageView main01 = new ImageView(getActivity());
        ImageView main02 = new ImageView(getActivity());
        ImageView main03 = new ImageView(getActivity());
        ImageView main04 = new ImageView(getActivity());

        main01.setBackgroundResource(R.drawable.main1);
        main02.setBackgroundResource(R.drawable.main2);
        main03.setBackgroundResource(R.drawable.mian3);
        main04.setBackgroundResource(R.drawable.mian5);

        pagersimages.add(main03);
        pagersimages.add(main04);
        pagersimages.add(main01);
        pagersimages.add(main02);
        adapter = new PagersAdapter(pagersimages);

    }

    private void startFilpping() {
        isAuto = true;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new AutoSingleTask(), 2,
                5, TimeUnit.SECONDS);

    }

    class AutoSingleTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (mviewPager) {
                currentitem = (currentitem + 1) % pagersimages.size();
                Log.i("Tag", "" + currentitem);
                System.out.println("" + currentitem);
                handler.sendEmptyMessage(100);
            }

        }

    }

    class ViewPagerListenner implements OnPageChangeListener {

        boolean isAutoPlay = false;

        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 0:
                    if (mviewPager.getCurrentItem() == mviewPager.getAdapter()
                            .getCount() - 1 && !isAuto) {
                        mviewPager.setCurrentItem(0);
                    } else if (mviewPager.getCurrentItem() == 0 && !isAuto) {
                        mviewPager
                                .setCurrentItem(mviewPager.getAdapter().getCount() - 1);
                    }
                    break;
                case 1:
                    isAuto = false;
                    break;
                case 2:
                    isAuto = true;
                    break;
                default:
                    break;
            }
        }


        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            currentitem = arg0;
            for (int i = 0; i < 4; i++) {
                if (i == arg0) {
                    ((View) dotimages.get(i))
                            .setBackgroundResource(R.drawable.point_pressed);
                } else {
                    ((View) dotimages.get(i))
                            .setBackgroundResource(R.drawable.point_unpressed);
                }
            }
        }

    }
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                isAuto = false;
                break;
            default: return false;
        }
        return false;
    }
}
