package com.healthy.wp.SmallCircle.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.healthy.wp.MyApplycation;
import com.healthy.wp.R;
import com.healthy.wp.SmallCircle.IT.ITRefreshUI;
import com.healthy.wp.SmallCircle.model.talkItem;
import com.healthy.wp.SmallCircle.presenter.CiclePresentor;
import com.healthy.wp.SmallCircle.presenter.gridAdapter;

import java.util.ArrayList;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)

public class SmallCircle extends Fragment implements ITRefreshUI {
    View view = null;
    ExpandableListView mlistview;
    ImageView back;
    expandlistviewadapter listviewadapter;
    SwipeRefreshLayout refresh;
    ScrollView sv;
    int height;
    ScrollView scroll;
    int width;
    List<String> strs = new ArrayList<String>();
    CiclePresentor ciclePresentor;
    List<talkItem> talkItems = new ArrayList<talkItem>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x123:
                    listviewadapter.notifyDataSetChanged();
                    setListViewHeightBasedOnChildren(mlistview);
                    break;
                case 0x234:
                    listviewadapter.viewHold.adapter.notifyDataSetChanged();
                    break;
            }

        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.mycircle, null);
        InitView();
        return view;
    }

    MyApplycation myApplycation;

    private void InitView() {
        // TODO Auto-generated method stub
        WindowManager wm = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        mlistview = (ExpandableListView) view.findViewById(R.id.my_cicel_list);
        scroll = (ScrollView) view.findViewById(R.id.scrollView1);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        back = (ImageView) view.findViewById(R.id.backme);
        sv = (ScrollView) view.findViewById(R.id.scrollView1);
        myApplycation = (MyApplycation) getActivity().getApplication();
        ciclePresentor = new CiclePresentor(getActivity(), this, myApplycation.getId());
        RelativeLayout main = (RelativeLayout) view.findViewById(R.id.main_linear);

        Bitmap bt1 = BitmapFactory.decodeResource(getResources(), R.drawable.smallbac);
        Log.i("imageview", "width: " + bt1.getWidth());
        Log.i("imageview", "height: " + bt1.getHeight());
        Bitmap bt2 = BitmapFactory.decodeResource(getResources(), R.drawable.my_pic);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ImageView ic = new ImageView(getActivity());
        ic.setImageResource(R.drawable.myicon);
        params.setMargins(800, bt1.getHeight() - (bt1.getHeight() - bt2.getHeight()) / 4, 0, 0);
        main.addView(ic, params);


        setListView();

    }

    private void setListView() {
        ciclePresentor.getfresh();

        listviewadapter = new expandlistviewadapter(talkItems);
        mlistview.setAdapter(listviewadapter);
        for (int i = 0; i < talkItems.size(); i++)
            mlistview.expandGroup(i);
        mlistview.setGroupIndicator(null);
        mlistview.setDividerHeight(4);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ciclePresentor.getfresh();
                handler.sendEmptyMessage(0x123);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止刷新
                        refresh.setRefreshing(false);
                    }
                }, 3000); // 3秒后发送消息，停止刷新
            }
        });
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        refresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        refresh.setProgressBackgroundColor(R.color.white); // 设定下拉圆圈的背景
        refresh.setSize(SwipeRefreshLayout.LARGE);
        mlistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i == 0)
                    refresh.setEnabled(true);
                else
                    refresh.setEnabled(false);

            }

        });
    }


    public void setListViewHeightBasedOnChildren(ExpandableListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        int dh = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            dh = listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + 200 + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = sv.getHeight() + height;
        listView.setLayoutParams(params);
    }

    public void RefreshUi(List<talkItem> talks) {
        talkItems.clear();
        for (int i = 0; i < talks.size(); i++) {
            talkItems.add(talks.get(i));
        }
        talks.clear();
        handler.sendEmptyMessage(0x123);
    }

    @Override
    public void upLoadsuccess() {

    }

    @Override
    public void uploadfail() {

    }

    @Override
    public void finishActivity() {

    }

    class expandlistviewadapter extends BaseExpandableListAdapter {
        Context context;
        List<talkItem> talks;
        ViewHold viewHold;
        ImageLoader.ImageListener listener;

        class ViewHold {
            TextView name;
            ImageView myic;
            TextView sbhead;
            TextView sbend;
            gridAdapter adapter;
            TextView showdetile;
            ImageView mypictrues;
            GridView wordview;
            ImageButton zan;
            ImageView talk;
        }

        public expandlistviewadapter(List<talkItem> talk) {
            ViewHold viewHold = new ViewHold();
            this.talks = talk;
        }

        public int getGroupCount() {
            return talks.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return talks.get(i).getTalks().size();
        }

        @Override
        public Object getGroup(int i) {
            return talks.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return talks.get(i).getTalks().get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                viewHold = new ViewHold();

                view = LayoutInflater.from(getActivity()).inflate(R.layout.group_item, viewGroup, false);
                viewHold.mypictrues = (ImageView) view.findViewById(R.id.mytuxiang);
                viewHold.name = (TextView) view.findViewById(R.id.sbname);
                viewHold.showdetile = (TextView) view.findViewById(R.id.worddetile);
                viewHold.wordview = (GridView) view.findViewById(R.id.gidpic);
                viewHold.adapter = new gridAdapter(getActivity());
                viewHold.adapter.setUrls(talks.get(i).getUrls());
                viewHold.wordview.setAdapter(viewHold.adapter);
                view.setTag(viewHold);
            }
            Log.d("Tag", i + "----" + talks.get(i).getUrls().toString());
            viewHold.adapter.setUrls(talks.get(i).getUrls());
            handler.sendEmptyMessage(0x234);
            listener = ImageLoader.getImageListener(viewHold.mypictrues, R.drawable.ic_launcher, R.drawable.ic_launcher);
            viewHold = (ViewHold) view.getTag();
            viewHold.name.setText(getResources().getString(R.string.sbname));
            viewHold.showdetile.setText(talks.get(i).getDynamicInformation());

            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                viewHold = new ViewHold();
                view = LayoutInflater.from(getActivity()).inflate(R.layout.child_item, viewGroup, false);
                viewHold.sbhead = (TextView) view.findViewById(R.id.sbhead);
                viewHold.sbend = (TextView) view.findViewById(R.id.sbend);
                view.setTag(viewHold);
            }
            viewHold = (ViewHold) view.getTag();
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }

}
