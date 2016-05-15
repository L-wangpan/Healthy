package com.healthy.wp.HttpUtils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by wan_g_000 on 2016/5/2.
 */

public class SongleVolley {
    private static volatile SongleVolley songleVolley=null;
    private RequestQueue mRequsetqueue=null;
    public static Context context;
    private ImageLoader mImageLoader;
//    ImageLoader.ImageListener listener;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private SongleVolley(Context context) {
        this.context = context;
        mImageLoader = new ImageLoader(mRequsetqueue, new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>(10);
            public Bitmap getBitmap(String s) {
                return cache.get(s);
            }
            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                cache.put(s,bitmap);
            }
        });
    }

    public static synchronized SongleVolley getInstance(Context context) {
        if (songleVolley == null) {
            songleVolley = new SongleVolley(context);
        }
        return songleVolley;
    }

    public RequestQueue getRequsetqueue() {
        if (mRequsetqueue == null) {
            mRequsetqueue = Volley.newRequestQueue(context);
        }
        return mRequsetqueue;
    }
    public <T>void addtoRequestQueue(Request<T> req)
    {
        getRequsetqueue().add(req);
    }
    public ImageLoader getmImageLoader(){
        return mImageLoader;
    }

}
