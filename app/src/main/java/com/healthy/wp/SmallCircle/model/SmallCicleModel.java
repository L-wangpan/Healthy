package com.healthy.wp.SmallCircle.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.healthy.wp.HttpUtils.SongleVolley;
import com.healthy.wp.HttpUtils.UrlConfig;
import com.healthy.wp.SmallCircle.IT.GetFreshData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wan_g_000 on 2016/4/29.
 */
public class SmallCicleModel {
    List<talkItem> talks = new ArrayList<talkItem>();

    public void getData(final GetFreshData gfdcallback, Context context, final String s) {
        StringRequest stringRequest  = new StringRequest(Request.Method.POST,UrlConfig.IP + "/healthAppService/PutUserDynamic",new Response.Listener() {

            @Override
            public void onResponse(Object arg0) {
                // TODO Auto-generated method stub
                try {
                    JSONObject js = new JSONObject(arg0.toString());
                    parseJson(js);
                    gfdcallback.success(talks);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                System.out.println("8888"+arg0.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                gfdcallback.fail();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
//                map.put("id",user.getUser_id());
                map.put("id",s);
                System.out.println(map);
                Log.d("Tag","----"+map.toString());
                return map;

            }
        };
        SongleVolley.getInstance(context).addtoRequestQueue(stringRequest);
    }

    public void parseJson(JSONObject js) {
        JSONArray firstarray = null;
        try {
            firstarray = js.getJSONArray(UrlConfig.INFORMATION);
            for (int i = 0; i < firstarray.length(); i++) {
                JSONObject obj = (JSONObject) firstarray.get(i);
                talkItem talk = new talkItem();
                talk.setDynamicDate(obj.getString(UrlConfig.DYNAMICDATE));
                talk.setDynamicInformation(obj.getString(UrlConfig.DYNAMICINFORMATION));
                JSONArray array = new JSONArray();
                array = obj.getJSONArray(UrlConfig.URL);
                for (int j=0;j<array.length();j++)
                {
                    JSONObject obj1 = (JSONObject) array.get(j);

                    talk.addURL(obj1.getString(UrlConfig.URL+(j+1)));

                }
                talks.add(talk);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
