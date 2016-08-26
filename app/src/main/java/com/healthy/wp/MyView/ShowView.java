package com.healthy.wp.MyView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.healthy.wp.Messure.View.DipTopX;
import com.healthy.wp.Personal.model.Constant;
import com.healthy.wp.R;
import com.healthy.wp.UserLogin.model.UserMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wan_g_000 on 2016/5/1.
 */
public class ShowView extends View {
    List<UserMessage> users;
    Paint p;
    static Canvas canvas;
    LinearLayout l1;
    Context context;
    String tag;
    int dwidth, dheight;
    int x1, y1, x2, y2, x, y;
    Date date;
    UserMessage user;


    public ShowView(Context context, List<UserMessage> us) {

        super(context);
        this.context = context;
        this.users = us;
        init();
    }

    public ShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void init() {
        p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        date = new Date();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode==MeasureSpec.EXACTLY&&heightMode==MeasureSpec.EXACTLY){
            setMeasuredDimension(widthSize,heightSize);
        }else if (widthMeasureSpec==MeasureSpec.EXACTLY){
            setMeasuredDimension(widthSize,widthSize);
        }else if (heightMeasureSpec==MeasureSpec.EXACTLY){
            setMeasuredDimension(heightSize,heightSize);
        }
        l1 = (LinearLayout) getParent();
        l1.getHeight();
        x1 = DipTopX.dipTopx(context, 40);
        y1 = DipTopX.dipTopx(context, 40);
        x2 = l1.getWidth() - DipTopX.dipTopx(context, 24);
        y2 = l1.getHeight() * 2 / 5;
        dwidth = (x2 - x1) / 5;
        dheight = (y2 - y1) / 5;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas = canvas;
        drawzhou(canvas);
    }

    public void drawzhou(Canvas canvas) {
        p.setColor(getResources().getColor(R.color.strock));

        p.setStrokeWidth(2);
        canvas.drawLine(x1, DipTopX.dipTopx(context, 30), x1, l1.getHeight() * 2 / 5, p);
        canvas.drawLine(x1, l1.getHeight() * 2 / 5, l1.getWidth() - DipTopX.dipTopx(context, 40), y2, p);
        for (int i = 1; i < 5; i++) {
            canvas.drawLine(x1, y2 - i * dheight, x1 + DipTopX.dipTopx(context, 10), y2 - i * dheight, p);
            canvas.drawLine(x1 + i * dwidth, y2, x1 + i * dwidth, y2 - DipTopX.dipTopx(context, 10), p);
        }

        p.setTextSize(DipTopX.dipTopx(context, 8));

        p.setColor(getResources().getColor(R.color.chengse));
        canvas.drawText(String.valueOf(date.getMonth() + 1) + "月", x1 - DipTopX.dipTopx(context, 4), y2 + DipTopX.dipTopx(context, 16), p);

        p.setColor(getResources().getColor(R.color.strock));
        canvas.drawLine(0, y2 + DipTopX.dipTopx(context, 80), l1.getWidth(), y2 + DipTopX.dipTopx(context, 80), p);
        canvas.drawLine(l1.getWidth() / 2, y2 + DipTopX.dipTopx(context, 80), l1.getWidth() / 2, y2 + DipTopX.dipTopx(context, 160), p);

        canvas.drawLine(0, y2 + DipTopX.dipTopx(context, 160), l1.getWidth(), y2 + DipTopX.dipTopx(context, 160), p);

        p.setStrokeWidth(2);
        p.setTextSize(36);
        p.setColor(getResources().getColor(R.color.transparent2));
        canvas.drawText(tag, l1.getWidth() / 4 - DipTopX.dipTopx(context, 12 * tag.length()), y2 + DipTopX.dipTopx(context, 100), p);
        p.setColor(getResources().getColor(R.color.black));
        p.setColor(getResources().getColor(R.color.transparent2));
        canvas.drawText("比上次", l1.getWidth() * 3 / 4 - DipTopX.dipTopx(context, 12 * tag.length()), y2 + DipTopX.dipTopx(context, 100), p);
//        for (int i = 0; i < users.size(); i++)
//            canvas.drawText(users.get(i-1).getDate() + "日", x1 + i * dwidth - DipTopX.dipTopx(context, 4), y2 + DipTopX.dipTopx(context, 16), p);
        if (tag.equals("重量")) {
            for (int i = 1; i < 6; i++)
                canvas.drawText(Constant.Xweight[i-1] + "", DipTopX.dipTopx(context, 20), y2 - i * dheight + DipTopX.dipTopx(context, 4), p);
            p.setColor(getResources().getColor(R.color.black));
            canvas.drawText(user.getWeight() + "千克", l1.getWidth() / 4 - tag.length() / 2 * DipTopX.dipTopx(context, 32), y2 + DipTopX.dipTopx(context, 120), p);

            drawWeights(x1, y2, canvas);
        } else if (tag.equals("脂肪")) {
            for (int i = 1; i < 6; i++)
                canvas.drawText(Constant.Xfat[i-1] + "", DipTopX.dipTopx(context, 20), y2 - i * dheight + DipTopX.dipTopx(context, 4), p);
            p.setColor(getResources().getColor(R.color.black));
            canvas.drawText(user.getFat() + "千克", l1.getWidth() / 4 - tag.length() / 2 * DipTopX.dipTopx(context, 32), y2 + DipTopX.dipTopx(context, 120), p);
            drawFats(x1, y2, canvas);
        } else if (tag.equals("水份")) {
            for (int i = 1; i < 6; i++)
                canvas.drawText(Constant.Xmoisture[i-1] + "", DipTopX.dipTopx(context, 20), y2 - i * dheight + DipTopX.dipTopx(context, 4), p);

            p.setColor(getResources().getColor(R.color.black));
            canvas.drawText(user.getMoisture() + "千克", l1.getWidth() / 4 - tag.length() / 2 * DipTopX.dipTopx(context, 32), y2 + DipTopX.dipTopx(context, 120), p);

            drawmoisture(x1, y2, canvas);
        }
    }


    public void drawWeights(int startX, int startY, Canvas canvas) {
        p.setColor(getResources().getColor(R.color.red));
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        for (int i = 0; i < users.size(); i++) {
//            DipTopX.dipTopx(context, 20), y2 - i * dheight + DipTopX.dipTopx(context, 4),
            canvas.drawCircle(startX+ dwidth * (i+1),startY- (int)( (Double.parseDouble(users.get(i).getWeight())/30) * dheight) + DipTopX.dipTopx(context, 4), 10, p);

            Log.d("Tag", i + "asas");
        }
    }

    public void drawFats(int startX, int startY, Canvas canvas) {
        p.setColor(getResources().getColor(R.color.red));
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        for (int i = 0; i < users.size() ; i++) {

            canvas.drawCircle(startX+ dwidth * (i+1),startY- (int)( (Double.parseDouble(users.get(i).getFatRate())/10) * dheight) + DipTopX.dipTopx(context, 4), 10, p);
            Log.d("Tag", i + "asas");
        }
    }

    public void drawmoisture(int startX, int startY, Canvas canvas) {
        p.setColor(getResources().getColor(R.color.red));
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        for (int i = 0; i < users.size()  ; i++) {
            canvas.drawCircle(startX+ dwidth * (i+1),startY- (int)( (Double.parseDouble(users.get(i).getMoisture())/20) * dheight) + DipTopX.dipTopx(context, 4), 10, p);

            Log.d("Tag", i + "asas");
        }
    }

    public void setTag(String s) {
        this.tag = s;
    }
    public void setUserPerson(UserMessage userPerson){
        this.user=userPerson;
        invalidate();
    }

    public void setUsers(List<UserMessage> users) {
        this.users = users;
        requestLayout();
        invalidate();
    }

    public int caculate() {
      /*  if(tag.equals("重量")){
            return chooseMaxWeight();
        }
        else if(tag.equals("体脂率")){

        }*/
        return 115;
    }

    public int chooseMaxHeight() {
        int max = Integer.parseInt(users.get(0).getHeight());
        for (int i = 1; i < users.size(); i++) {
            if (max < Integer.parseInt(users.get(i).getHeight())) {
                max = Integer.parseInt(users.get(i).getHeight());
            }
        }
        return max;
    }

    public int chooseMinHeight() {
        int min = Integer.parseInt(users.get(0).getHeight());
        for (int i = 1; i < users.size(); i++) {
            if (min > Integer.parseInt(users.get(i).getHeight())) {
                min = Integer.parseInt(users.get(i).getHeight());
            }
        }
        return min;
    }

    public int chooseMaxWeight() {
        int max = Integer.parseInt(users.get(0).getWeight());
        for (int i = 1; i < users.size(); i++) {
            if (max < Integer.parseInt(users.get(i).getHeight())) {
                max = Integer.parseInt(users.get(i).getHeight());
            }
        }
        return max;
    }

    public int chooseMinWeight() {
        int min = Integer.parseInt(users.get(0).getWeight());
        for (int i = 1; i < users.size(); i++) {
            if (min > Integer.parseInt(users.get(i).getWeight())) {
                min = Integer.parseInt(users.get(i).getWeight());
            }
        }
        return min;
    }

    public int chooseMaxFat() {
        int max = Integer.parseInt(users.get(0).getFat());
        for (int i = 1; i < users.size(); i++) {
            if (max < Integer.parseInt(users.get(i).getFat())) {
                max = Integer.parseInt(users.get(i).getFat());
            }
        }
        return max;
    }

    public int chooseMinFat() {
        int min = Integer.parseInt(users.get(0).getFat());
        for (int i = 1; i < users.size(); i++) {
            if (min > Integer.parseInt(users.get(i).getFat())) {
                min = Integer.parseInt(users.get(i).getFat());
            }
        }
        return min;
    }

    public void setUser(List<UserMessage> user) {
        this.users = users;

        Log.d("Tag", "users");
//        postInvalidateDelayed(500, 0, 0, getWidth(), getHeight());
//        invalidate(100,100,200,200);
        invalidate();
    }

}
