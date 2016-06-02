package com.healthy.wp.HttpUtils;

import android.graphics.Bitmap;
import android.util.Log;

import com.healthy.wp.MyView.ImageThumbnail;
import com.healthy.wp.Personal.View.SetUpActivity;
import com.healthy.wp.SmallCircle.IT.ITRefreshUI;
import com.healthy.wp.gallery.king.photo.util.Bimp;
import com.healthy.wp.gallery.king.photo.util.FileUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

/**
 * Created by wan_g_000 on 2016/5/21.
 */
public class SingleAsyncHttp {
    public static void post(Bitmap bitmap, String user_id) throws FileNotFoundException {

            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("id", user_id);
            params.put("userpic", FileUtils.getFileBitmap(ImageThumbnail.PicZoom(bitmap,200,200),System.currentTimeMillis()+""));
            client.post(UrlConfig.IP + "/healthAppService/UploadUserPhoto", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Log.d("Tag", new String(responseBody));
                            try {
                                JSONObject js = new JSONObject(new String(responseBody).toString());
                                SetUpActivity.myApplycation.getUser().setHeadPortrait(UrlConfig.IP + js.getString(UrlConfig.HEADPORTRAIT));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    }

            );


    }

    public static void Mutilpost( String id, String detile, final ITRefreshUI refreshUI) throws FileNotFoundException {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put(UrlConfig.DYNAMICDATE, getCurrentTime());
        params.put(UrlConfig.DYNAMICINFORMATION, detile);
        Log.d("Tag", "size --" + Bimp.tempSelectBitmap.size());
        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
            params.put("userpic" + i + "", FileUtils.getFileBitmap(ImageThumbnail.PicZoom(Bimp.tempSelectBitmap.get(i).getBitmap(),200,200),System.currentTimeMillis()+""));
        }


        client.post(UrlConfig.IP + "/healthAppService/GetUserDynamic", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("Tag", "ok");
                refreshUI.upLoadsuccess();
                refreshUI.finishActivity();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                refreshUI.uploadfail();
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);

            }
        });


    }

    public static File compressBmpToFile(Bitmap bmp, File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;
        if (!file.exists()) {
            file.mkdirs();
        }
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(file);

            Log.d("Tag", "okokokok");
            fos.write(baos.toByteArray());

            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Tag", "sadsad");
        }
        return file;
    }

    public static String getCurrentTime() {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestring = format.format(d);
        return datestring;
    }

    public static String getMyBitmappath() {
        String path  = android.os.Environment.getExternalStorageDirectory()+"/health/";

        File dirpath = new File(path);
        if (!dirpath.exists())
            dirpath.mkdirs();
        String filename = System.currentTimeMillis() + ".jpg";
        File tempFile = new File(dirpath, filename);
        return tempFile.getAbsolutePath();
    }
}
