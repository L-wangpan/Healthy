package com.healthy.wp.SmallCircle.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.NetworkImageView;
import com.healthy.wp.HttpUtils.SongleVolley;
import com.healthy.wp.HttpUtils.UrlConfig;
import com.healthy.wp.R;

import java.util.List;

/**
 * Created by wan_g_000 on 2016/5/28.
 */
public class gridAdapter extends BaseAdapter {
    List<String> urls;
    Context context;
    ViewHolder viewHolder;

    public gridAdapter(Context context) {
        this.context = context;
        viewHolder = new ViewHolder();
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int i) {
        return urls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {

            view = LayoutInflater.from(context).inflate(R.layout.griditen, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (NetworkImageView) view.findViewById(R.id.network_image_view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.image.setDefaultImageResId(R.drawable.logo);
        viewHolder.image.setErrorImageResId(R.drawable.ic_launcher);
        viewHolder.image.setImageUrl(UrlConfig.IP+urls.get(i),
                SongleVolley.getInstance(context).getmImageLoader());
//        ImageLoader.ImageListener listener = ImageLoader.getImageListener(viewHolder.image,
//                R.drawable.logo, R.drawable.ic_launcher);
//        SongleVolley.getInstance(context).getmImageLoader().get(UrlConfig.IP+urls.get(i),listener);
        return view;
    }

    class ViewHolder {
        NetworkImageView image;
    }

}
