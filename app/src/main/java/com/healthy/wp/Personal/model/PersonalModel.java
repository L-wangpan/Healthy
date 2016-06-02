package com.healthy.wp.Personal.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.healthy.wp.HttpUtils.SongleVolley;
import com.healthy.wp.HttpUtils.UrlConfig;
import com.healthy.wp.Personal.IT.ITmodel;
import com.healthy.wp.UserLogin.model.UserMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan_g_000 on 2016/5/3.
 */
public class PersonalModel {
    public void refreshPesonal(final ITmodel model, Context context,final historyData data){
        String url = UrlConfig.IP+"/healthAppService/GetDetails";
        StringRequest stringRequest  = new StringRequest(Request.Method.POST,
                url, new Response.Listener() {

            @Override
            public void onResponse(Object arg0) {
                // TODO Auto-generated method stub
                try {
                    UserMessage user = new UserMessage();
                    JSONObject js = new JSONObject(arg0.toString());
                    user.setBoneMass(js.getString(UrlConfig.BONEMASS));
                    user.setFatFreeMass(js.getString(UrlConfig.FATFREEMASS));
                    user.setFatRate(js.getString(UrlConfig.FATRATE));
                    user.setMassIndex(js.getString(UrlConfig.MASSINDEX));
                    user.setMoisture(js.getString(UrlConfig.MOISTURE));
                    user.setFat(js.getString(UrlConfig.FAT));
                    System.out.println("6666"+js.toString());
                    Log.d("Tag","---->"+js.toString());
                    model.userUpdate(user);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                System.out.println("8888"+arg0.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                model.fail(0);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("id",data.getUser_id());
                map.put(UrlConfig.NICKNAME, data.getNickname());
                Log.d("Tag","----"+((double)Double.parseDouble(data.getHeight())/100)+"");
                map.put(UrlConfig.HEIGHT,((double)Double.parseDouble(data.getHeight())/100)+"");
                map.put(UrlConfig.WEIGHT,data.getWeight());
                map.put(UrlConfig.USER_DATE,data.getBorn());
                map.put(UrlConfig.SEX,data.getSex());
                map.put(UrlConfig.USERDETAILSDATE,getCurrentTime());
                System.out.println(map);
                Log.d("Tag","----"+map.toString());
                return map;

            }
        };
        SongleVolley.getInstance(context).addtoRequestQueue(stringRequest);
    }
    public static String getCurrentTime(){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = format.format(d);
        return datestring;
    }

}
