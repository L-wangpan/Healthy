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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan_g_000 on 2016/3/14.
 */
public class LoginModel {
    LoginCallBack callBack;
    UserMessage userMessage;
    Context context;

    public LoginModel(UserMessage user,Context context, LoginCallBack loginCallBack) {
        this.userMessage=user;
        this.callBack=loginCallBack;
    }

    public LoginModel(Context context) {
            this.context=context;
    }

    public void Login(final UserMessage user, final LoginCallBack loginCallBack){
        String url = "http://xywangpan.cn:8081/healthy/servlet/LandServlet";
//                String url="http://192.168.253.2:8080/healthAppService/login";
                System.out.println("asdas"+url);
//                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest stringrequest;
                System.out.println("77777777777");
                stringrequest = new StringRequest(Request.Method.POST,
                        url, new Response.Listener() {

                    @Override
                    public void onResponse(Object arg0) {
                        // TODO Auto-generated method stub
                        try {

                            JSONObject js = new JSONObject(arg0.toString());
                            System.out.println("6666"+js.toString());
                            UserMessage userthis =  parsejson(js);
                            loginCallBack.Success(userthis);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }


                        System.out.println("8888"+arg0.toString());
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
                        map.put("user_id", user.getUsername());
                        map.put("password", user.getPassword());
                        System.out.println(map);
                        return map;
                    }
                };
//        requestQueue.add(stringrequest);
        SongleVolley.getInstance(context).addtoRequestQueue(stringrequest);
    }
    public void regist(final UserMessage user, final LoginCallBack loginCallBack){
        String url = "http://xywangpan.cn:8081/healthy/servlet/RegistServlet";
//                String url="http://192.168.253.2:8080/healthAppService/login";
        StringRequest stringrequest;
        stringrequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener() {

            @Override
            public void onResponse(Object arg0) {
                // TODO Auto-generated method stub
                try {

                    JSONObject js = new JSONObject(arg0.toString());
                    System.out.println("6666"+js.toString());
                    UserMessage userthis =  parsejson(js);
                    loginCallBack.Success(user);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"网络错误",Toast.LENGTH_LONG).show();
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
            Log.d("Tag",js.toString());
            user.setCode(js.getString("code"));
            Log.d("Tag",user.getCode()+"");
            System.out.println(user.getCode()+"9999");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }


}
