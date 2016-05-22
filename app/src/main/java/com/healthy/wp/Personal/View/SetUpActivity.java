package com.healthy.wp.Personal.View;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.healthy.wp.HttpUtils.SingleAsyncHttp;
import com.healthy.wp.MyApplycation;
import com.healthy.wp.MyView.ImageThumbnail;
import com.healthy.wp.Personal.IT.RefreshIT;
import com.healthy.wp.Personal.model.PlanResult;
import com.healthy.wp.Personal.model.historyData;
import com.healthy.wp.Personal.presenter.PersonPresenter;
import com.healthy.wp.R;
import com.healthy.wp.UserLogin.View.MyActionbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class SetUpActivity extends Activity implements RefreshIT {

    View v;
    private EditText name, born, height, weight, bmi, fat, fat_lv, lean, water, personal_des;
    Intent intent;
    TextView sex;
    ImageView change_icon;
    PersonPresenter p;
    PopupWindow popupWindow;
    historyData datas;
    LinearLayout linearLayout;
    View vwindow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        initView();

    }

    MyApplycation myApplycation;
    Button take_photo, camera;

    private void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        v = inflater.inflate(R.layout.action, null);
        sex = (TextView) findViewById(R.id.sexchange);
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
        myApplycation = new MyApplycation();
        take_photo = (Button) vwindow.findViewById(R.id.taker);
        camera = (Button) vwindow.findViewById(R.id.camera);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
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
                    String id =((MyApplycation)getApplication()).getId();
                    Log.d("Tag","asdasd"+"---"+id);
                    datas = new historyData(id,name.getText().toString(), height.getText().toString(), weight.getText().toString(),sex.getText().toString(),born.getText().toString());

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
        String[] proj = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){;
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
                if (selectedImageUri != null) {
                    try {
                        bitMap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(selectedImageUri));
                        String url = getRealPathFromURI(selectedImageUri);

                        File f = new File(url);
                        System.out.println(url);
                        SingleAsyncHttp.post(f,myApplycation.getId());
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
                Uri selectedImageUri1 = data.getData();
                if (selectedImageUri1 != null) {
                    try {
                        bitMap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(selectedImageUri1));
                        String url = getRealPathFromURI(selectedImageUri1);

                        File f = new File(url);
                        System.out.println(url);
                        SingleAsyncHttp.post(f,myApplycation.getId());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                bitMap = (Bitmap) bundle.get("data");
                if (bitMap != null)
                    bitMap.recycle();
                bitMap = (Bitmap) data.getExtras().get("data");
                int scale = ImageThumbnail.reckonThumbnail(bitMap.getWidth(),
                        bitMap.getHeight(), 94, 94);
                bitMap = ImageThumbnail.PicZoom(bitMap,
                        (int) (bitMap.getWidth() / scale),
                        (int) (bitMap.getHeight() / scale));
                String s = ImageThumbnail.savaPhotoToLocal(getIntent(), bitMap);
                change_icon.setImageBitmap(bitMap);
                change_icon.setVisibility(View.VISIBLE);
                break;
        }
        popupWindow.dismiss();
    }


}
