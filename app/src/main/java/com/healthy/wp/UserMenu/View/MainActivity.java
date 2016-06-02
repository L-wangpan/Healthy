package com.healthy.wp.UserMenu.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.healthy.wp.Messure.View.MessureFragment;
import com.healthy.wp.MyApplycation;
import com.healthy.wp.Personal.View.MyFragment;
import com.healthy.wp.Personal.View.SetUpActivity;
import com.healthy.wp.R;
import com.healthy.wp.SmallCircle.View.SmallCircle;
import com.healthy.wp.UserLogin.View.MyActionbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wan_g_000 on 2016/3/23.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    ViewPager viewPager;
    ImageButton left;
    ImageButton right;
    RadioButton bhome, bassess, bsmall, bmy;
    List<Fragment> fragments = new ArrayList<Fragment>();
    View v;
    MyApplycation myApplycation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (getActionBar() == null) {
            Log.d("Tag", "null");
        } else {
            Log.d("Tag", "ok");
        }
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        v = inflater.inflate(R.layout.action, null);
        MyActionbar.setActionBar(getActionBar(), v, "首页",
                1);
        iniView();
    }

    private void iniView() {
        // TODO Auto-generated method stub
//        SongleVolley.getInstance(getApplication());
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        bhome = (RadioButton) findViewById(R.id.home);
        bassess = (RadioButton) findViewById(R.id.assess);
        bsmall = (RadioButton) findViewById(R.id.small_circle);
        bmy = (RadioButton) findViewById(R.id.my_center);
        fragments.add(new MessureFragment());
        fragments.add(new headFragment());
        fragments.add(new SmallCircle());
        fragments.add(new MyFragment());
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setOnPageChangeListener(new PageChangeListener());
        right = (ImageButton) getActionBar().getCustomView().findViewById(R.id.right);
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.home:
                viewPager.setCurrentItem(0);
//                MyActionbar.setActionBar(getActionBar(), v,
//                        "首页", 1);
                bhome.setTextColor(getResources().getColor(R.color.bg_color_press));
                bassess.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                bsmall.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                bmy.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                break;
            case R.id.assess:
                viewPager.setCurrentItem(1);
//                MyActionbar.setActionBar(getActionBar(), v,
//                        "测评", 3);
                bhome.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                bassess.setTextColor(getResources().getColor(R.color.bg_color_press));
                bsmall.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                bmy.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                break;
            case R.id.small_circle:
                viewPager.setCurrentItem(2);
//                MyActionbar.setActionBar(getActionBar(), v,
//                        "小圈", 1);
                bhome.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                bassess.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                bsmall.setTextColor(getResources().getColor(R.color.bg_color_press));
                bmy.setTextColor(getResources().getColor(R.color.bg_color_nomal));

                break;
            case R.id.my_center:
                viewPager.setCurrentItem(3);
//                MyActionbar.setActionBar(getActionBar(), v,
//                        "我", 1);
                bhome.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                bassess.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                bsmall.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                bmy.setTextColor(getResources().getColor(R.color.bg_color_press));
                break;
            default:
                break;
        }

    }

    class SencondLisenner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, com.healthy.wp.gallery.king.photo.activity.MainActivity.class));
        }
    }

    class ThrirdLisenner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, SetUpActivity.class));

        }
    }

    class PageChangeListener implements ViewPager.OnPageChangeListener {

        public void onPageScrollStateChanged(int arg0) {
            Log.d("Tag", arg0 + "-------");

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageSelected(int id) {
            switch (id) {
                case 0:
                    bhome.setChecked(true);
                    MyActionbar.setActionBar(getActionBar(),
                            v, "首页", 1);
                    bhome.setTextColor(getResources().getColor(R.color.bg_color_press));
                    bassess.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    bsmall.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    bmy.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    break;
                case 1:
                    bassess.setChecked(true);
                    MyActionbar.setActionBar(getActionBar(),
                            v, "测评", 1);
                    bhome.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    bassess.setTextColor(getResources()
                            .getColor(R.color.bg_color_press));
                    bsmall.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    bmy.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    break;
                case 2:

                    bsmall.setChecked(true);
                    MyActionbar.setActionBar(getActionBar(),
                            v, "小圈", 6);
                    right.setOnClickListener(new SencondLisenner());
                    bhome.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    bassess.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    bsmall.setTextColor(getResources().getColor(R.color.bg_color_press));
                    bmy.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    break;
                case 3:
                    bmy.setChecked(true);
                    MyActionbar.setActionBar(getActionBar(),
                            v, "我", 4);
                    right.setOnClickListener(new ThrirdLisenner());
                    bhome.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    bassess.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    bsmall.setTextColor(getResources().getColor(R.color.bg_color_nomal));
                    bmy.setTextColor(getResources().getColor(R.color.bg_color_press));
                    break;
                default:
                    break;
            }
        }

    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

    }

}