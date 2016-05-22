package com.healthy.wp.HttpUtils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by wan_g_000 on 2016/5/21.
 */
public class SingleAsyncHttp {
    public static void post(File f,String user_id) throws FileNotFoundException {

        if(f.exists() && f.length()>0){
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("userpic",f);
            params.put(UrlConfig.USER_NAME,user_id);
            client.post("http://192.168.1.100:8080/web/UploadFile", params,new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] responseBody, Throwable error) {
                }
            });
        }else{

        }

    }

}
