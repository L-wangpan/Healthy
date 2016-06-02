package com.healthy.wp.Personal.View;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.healthy.wp.HttpUtils.SingleAsyncHttp;
import com.healthy.wp.HttpUtils.SongleVolley;
import com.healthy.wp.MyApplycation;
import com.healthy.wp.MyView.ImageThumbnail;
import com.healthy.wp.Personal.IT.RefreshIT;
import com.healthy.wp.Personal.model.PlanResult;
import com.healthy.wp.Personal.model.historyData;
import com.healthy.wp.Personal.presenter.PersonPresenter;
import com.healthy.wp.R;
import com.healthy.wp.UserLogin.View.MyActionbar;
import com.healthy.wp.UserLogin.model.UserMessage;

import java.io.FileNotFoundException;
import java.util.List;

public class SetUpActivity extends Activity implements RefreshIT {

    View v;
    private EditText name, born, height, weight, bmi, fat, fat_lv, lean, water, personal_des;
    Intent intent;
    EditText sex;
    ImageView change_icon;
    PersonPresenter p;
    PopupWindow popupWindow;
    historyData datas;
    LinearLayout linearLayout;
    View vwindow;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        initView();

    }

    public static MyApplycation myApplycation;
    Button take_photo, camera;

    private void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        v = inflater.inflate(R.layout.action, null);
        sex = (EditText) findViewById(R.id.sexchange);
        name = (EditText) findViewById(R.id.choose_name);
        born = (EditText) findViewById(R.id.choose_born);
        linearLayout = (LinearLayout) findViewById(R.id.parent);
        height = (EditText) findViewById(R.id.choose_height);
        weight = (EditText) findViewById(R.id.choose_weight);
        bmi = (EditText) findViewById(R.id.choose_bmi);
        fat = (EditText) findViewById(R.id.choose_fat);
        fat_lv = (EditText) findViewById(R.id.choose_fat_lv);
        lean = (EditText) findViewById(R.id.choose_lean);
        water = (EditText) findViewById(R.id.choose_water);
        personal_des = (EditText) findViewById(R.id.jianjie);
        change_icon = (ImageView) findViewById(R.id.change_my_pic);
        vwindow = getLayoutInflater().inflate(R.layout.dialog, null);
        popupWindow = new PopupWindow(vwindow, 800, 300);
        myApplycation = (MyApplycation) getApplication();

        bmi.setText(myApplycation.getUser().getMassIndex());
        water.setText(myApplycation.getUser().getMoisture());
        fat_lv.setText(myApplycation.getUser().getFatRate());
        lean.setText(myApplycation.getUser().getFatFreeMass());
        fat.setText(myApplycation.getUser().getFat());
        born.setText(myApplycation.getUser().getDate());
        name.setText(myApplycation.getUser().getNickname());
        take_photo = (Button) vwindow.findViewById(R.id.taker);
        height.setText((int)((Double.parseDouble(myApplycation.getUser().getHeight()))*100)+"");
        weight.setText(myApplycation.getUser().getWeight());
        camera = (Button) vwindow.findViewById(R.id.camera);
        sex.setText(myApplycation.getUser().getSex());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        Log.d("Tag", "asdadsdsad" + myApplycation.getUser().getHeadPortrait());
        ImageRequest imageRequest = new ImageRequest(myApplycation.getUser().getHeadPortrait(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                change_icon.setImageBitmap(bitmap);
            }
        }, 150, 150, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                change_icon.setImageResource(R.drawable.my_pic);
            }
        });
        SongleVolley.getInstance(getApplication()).addtoRequestQueue(imageRequest);

        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1002);
                popupWindow.dismiss();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1001);
                popupWindow.dismiss();
            }

        });
        change_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dimBackground(1.0f, 0.1f);
                popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 20, 20);

            }
        });
        p = new PersonPresenter(this);
        MyActionbar.setActionBar(getActionBar(), v, "评测数据",
                5);
        getActionBar().getCustomView().findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ;
        getActionBar().getCustomView().findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (height.getText().toString().length() == 0) {
                    Toast.makeText(getApplication(), "请填写您的身高", Toast.LENGTH_LONG).show();
                } else if (weight.getText().toString().length() == 0) {
                    Toast.makeText(getApplication(), "请填写您的体重", Toast.LENGTH_LONG).show();
                } else {
//                    Date d = new Date();
                    String id = ((MyApplycation) getApplication()).getId();
                    Log.d("Tag", "asdasd" + "---" + id);
                    datas = new historyData(id, name.getText().toString(), height.getText().toString(), weight.getText().toString(), sex.getText().toString(), born.getText().toString());
                    p.refreshPersonal(datas, getApplication());

                }

            }
        });
    }


    @Override
    public void Error(int code) {
        Toast.makeText(getApplication(), "保存失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(String path) {
        Toast.makeText(getApplication(), "保存成功", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void RefreshUI(PlanResult ps) {

    }

    @Override
    public void RefreshIdentify(UserMessage user) {
        bmi.setText(user.getMassIndex());
        water.setText(user.getMoisture());
        fat_lv.setText(user.getFatRate());
        lean.setText(user.getFatFreeMass());
        fat.setText(user.getFat());
        myApplycation.getUser().setSex(sex.getText().toString());
        myApplycation.getUser().setNickname(name.getText().toString());
        myApplycation.getUser().setDate(born.getText().toString());
        myApplycation.getUser().setWeight(weight.getText().toString());
        myApplycation.getUser().setHeight((int)(Double.parseDouble(height.getText().toString())/100)+"");
        myApplycation.getUser().setMassIndex(bmi.getText().toString());
        myApplycation.getUser().setMoisture(water.getText().toString());
        myApplycation.getUser().setFatFreeMass(lean.getText().toString());
        myApplycation.getUser().setFat(fat.getText().toString());
        myApplycation.getUser().setFatRate(fat_lv.getText().toString());
        Toast.makeText(getApplication(),"保存成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void RefreshMyId(List<historyData> datas) {

    }

    private void dimBackground(final float from, final float to) {
        final Window window = getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (Float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });

        valueAnimator.start();
    }

    Bitmap bitMap = null;

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            ;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);

            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case 1001: // 从本地选择图片
                if (bitMap != null && !bitMap.isRecycled()) {
                    bitMap.recycle();
                }
                Uri selectedImageUri = data.getData();
               Log.d("Tag","wrong--"+selectedImageUri.toString());
                if (selectedImageUri != null) {
                    try {
                        bitMap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(selectedImageUri));
                        ImageThumbnail.PicZoom(bitMap,150,150);
                        Log.d("Tag", myApplycation.getId() + "---00000");
                        SingleAsyncHttp.post(bitMap, myApplycation.getId());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // 下面这两句是对图片按照一定的比例缩放。
                    int scale = ImageThumbnail.reckonThumbnail(bitMap.getWidth(),
                            bitMap.getHeight(), 94, 94);
                    bitMap = ImageThumbnail.PicZoom(bitMap,
                            (int) (bitMap.getWidth() / scale),
                            (int) (bitMap.getHeight() / scale));
                    change_icon.setImageBitmap(bitMap);
                }
                break;
            case 1002: // 拍照

                data.getData();
                Bundle bundle = data.getExtras();
                bitMap = (Bitmap) bundle.get("data");
                if (bitMap != null)
                    bitMap.recycle();
                bitMap = (Bitmap) data.getExtras().get("data");
                bitMap = ImageThumbnail.PicZoom(bitMap,
                        150,
                        100);
                change_icon.setImageBitmap(bitMap);
                try {

                    SingleAsyncHttp.post(bitMap, myApplycation.getId());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
        popupWindow.dismiss();
    }

    public static String GetFilePathFor(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"+ split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] { split[1] };

                return getDataColumn(context, contentUri, selection,selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    public static String getDataColumn(Context context, Uri uri, String selection,String[] selectionArgs) {
        Cursor cursor = null;
        final String[] projection = { MediaStore.Images.Media.DATA };
        try {
            cursor = context.getContentResolver().query(uri, projection,selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }



    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
