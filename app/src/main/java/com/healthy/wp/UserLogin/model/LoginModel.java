package com.healthy.wp.UserLogin.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.healthy.wp.HttpUtils.SongleVolley;
import com.healthy.wp.HttpUtils.UrlConfig;
import com.healthy.wp.UserLogin.ITF.LoginCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wan_g_000 on 2016/3/14.
 */
public class LoginModel {
    LoginCallBack callBack;
    UserMessage userMessage;
    Context context;

    public LoginModel(UserMessage user, Context context, LoginCallBack loginCallBack) {
        this.userMessage = user;
        this.callBack = loginCallBack;
    }

    public LoginModel(Context context) {
        this.context = context;
    }

    public void Login(final UserMessage user, final LoginCallBack loginCallBack) {

//        String url = "http://xywangpan.cn:8081/healthy/servlet/LandServlet";
        String url = UrlConfig.IP+"/healthAppService/login";
        System.out.println("asdas" + url);
//                RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringrequest;
        System.out.println("77777777777");
        stringrequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener() {

            @Override
            public void onResponse(Object arg0) {
                // TODO Auto-generated method stub
                String s = "";
                try {
                    try {
                        s = new String(arg0.toString().getBytes(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    JSONObject js = new JSONObject(s);

                    final UserMessage userthis = parsejson(js);
                    Log.d("Tag", "6666--" + userthis.toString());
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            loginCallBack.Success(userthis);
                        }
                    };
                    timer.schedule(task, 1000);


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                System.out.println("8888" + arg0.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put(UrlConfig.USER_NAME, user.getUsername());
                map.put(UrlConfig.USER_PASSWORD, user.getPassword());

//                        map.put("user_id", user.getUsername());
//                        map.put("password", user.getPassword());
                System.out.println(map);
                return map;
            }
        };
//        requestQueue.add(stringrequest);
        SongleVolley.getInstance(context).addtoRequestQueue(stringrequest);
    }

    public void regist(final UserMessage user, final LoginCallBack loginCallBack) {
        String url = UrlConfig.IP+"/healthAppService/Register";
//                String url="http://192.168.253.2:8080/healthAppService/login";
        StringRequest stringrequest;
        stringrequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener() {

            @Override
            public void onResponse(Object arg0) {
                // TODO Auto-generated method stub
                try {

                    JSONObject js = new JSONObject(arg0.toString());
                    Log.d("Tag", "66666" + js.toString());
                    System.out.println("6666" + js.toString());
                    UserMessage userthis = parsejson(js);
                    Log.d("Tag", "bbbbbbbbb" + userthis.toString());
                    loginCallBack.Success(userthis);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络错误", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put(UrlConfig.USER_NAME, user.getUsername());
                map.put(UrlConfig.USER_PASSWORD, user.getPassword());
                System.out.println(map);
                return map;
            }
        };
        SongleVolley.getInstance(context).addtoRequestQueue(stringrequest);
    }

    public UserMessage parsejson(JSONObject js) throws JSONException {
        UserMessage user = new UserMessage();
        try {
            Log.d("Tag","aaaaa"+js.toString());
            user.setCode(js.getString("code"));
            user.setUsername(js.getString(UrlConfig.USER_NAME));
            user.setId(js.getString("id"));
            user.setHeadPortrait(UrlConfig.IP + js.getString(UrlConfig.HEADPORTRAIT));
            user.setNickname(js.getString(UrlConfig.NICKNAME));
            user.setHeight(js.getDouble(UrlConfig.HEIGHT) + "");
            user.setWeight(js.getDouble(UrlConfig.WEIGHT) + "");
            user.setSex(js.getString(UrlConfig.SEX));
            user.setDate(js.getString(UrlConfig.USER_DATE));

            user.setMoisture(js.getString(UrlConfig.MOISTURE));
            user.setFat(js.getString(UrlConfig.FAT));
            user.setAge(getYear(js.getString(UrlConfig.USER_DATE)));
            user.setMassIndex(js.getDouble(UrlConfig.MASSINDEX) + "");
            user.setBoneMass(js.getDouble(UrlConfig.BONEMASS) + "");
            user.setFatFreeMass(js.getDouble(UrlConfig.FATFREEMASS) + "");
            user.setFatRate(js.getDouble(UrlConfig.FATRATE) + "");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getYear(String date) {
        Date d;
        Date now = new Date();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            d = format.parse(date);
        } catch (ParseException e) {
            return "1990";
        }
        return (now.getYear()-d.getYear())+"";
    }

}
