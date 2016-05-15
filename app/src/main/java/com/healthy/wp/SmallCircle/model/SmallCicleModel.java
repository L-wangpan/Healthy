package com.healthy.wp.SmallCircle.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.healthy.wp.HttpUtils.SongleVolley;
import com.healthy.wp.SmallCircle.IT.GetFreshData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wan_g_000 on 2016/4/29.
 */
public class SmallCicleModel {
    public void getData(final GetFreshData gfdcallback, Talk tk, Context context, String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
        null,
        new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {
                List<Talk> talks = new ArrayList<Talk>();
                Log.d("TAG", response.toString());
                gfdcallback.success(talks);
            }
        }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        SongleVolley.getInstance(context).addtoRequestQueue(jsonObjectRequest);
    }
}
