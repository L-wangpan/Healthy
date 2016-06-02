package com.healthy.wp.UserMenu.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.healthy.wp.HttpUtils.SongleVolley;
import com.healthy.wp.HttpUtils.UrlConfig;
import com.healthy.wp.UserLogin.model.*;
import com.healthy.wp.UserLogin.model.UserMessage;
import com.healthy.wp.UserMenu.ITF.CallbackHistory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pan on 2016/6/1.
 */
public class HistoryModel {
    List<UserMessage> users = new ArrayList<UserMessage>();

    public void getHistory(final String userid, final CallbackHistory history, Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                UrlConfig.IP + "/healthAppService/PutHistory", new Response.Listener() {

            @Override
            public void onResponse(Object arg0) {
                // TODO Auto-generated method stub
                try {
                    JSONObject js = new JSONObject(arg0.toString());
                    parseJson(js.toString());
                    history.RefreshData(users);
                    System.out.println("6666" + js.toString());
                    Log.d("Tag", "PuT---->" + js.toString());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                System.out.println("8888" + arg0.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Tag", "fail");
                history.fail();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", userid);
                Log.d("Tag", "PUT" + userid);
                return map;

            }
        };
        SongleVolley.getInstance(context).getRequsetqueue().add(stringRequest);
    }

    public void parseJson(String js) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(js);
            JSONArray array = obj.getJSONArray(UrlConfig.HISTORY);
            for (int i=0;i<array.length();i++){
                UserMessage userMessage = new UserMessage();
                JSONObject jsonObject = (JSONObject) array.get(i);
                Log.d("Tag","history"+jsonObject.toString());
                userMessage.setDate(jsonObject.getString(UrlConfig.HISTORYDATE));
                userMessage.setFatRate(jsonObject.getString(UrlConfig.FATRATE));
                userMessage.setMoisture(jsonObject.getString(UrlConfig.MOISTURE));
                userMessage.setWeight(jsonObject.getString(UrlConfig.WEIGHT));
                users.add(userMessage);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
