package com.healthy.wp.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.healthy.wp.Messure.View.DipTopX;
import com.healthy.wp.R;
import com.healthy.wp.UserLogin.model.UserMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wan_g_000 on 2016/5/1.
 */
public class ShowView extends View {
    List<UserMessage> users = new ArrayList<UserMessage>();
    Paint p;
    LinearLayout l1;
    Context context;
    String tag = "重量";
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
        for (int i = 1; i < 6; i++)
            canvas.drawText((caculate() * i / 4) + "", DipTopX.dipTopx(context, 20), y2 - i * dheight + DipTopX.dipTopx(context, 4), p);

        p.setColor(getResources().getColor(R.color.chengse));
        canvas.drawText(String.valueOf(date.getMonth() + 1) + "月", x1 - DipTopX.dipTopx(context, 4), y2 + DipTopX.dipTopx(context, 16), p);
        for (int i = 1; i < 6; i++)
            canvas.drawText(String.valueOf(date.getDay() + 1 + i * 7) + "日", x1 + i * dwidth - DipTopX.dipTopx(context, 4), y2 + DipTopX.dipTopx(context, 16), p);
        if (tag.equals("重量")) {
            drawWeights(x1, y2,canvas);
        } else if (tag.equals("脂肪")) {
            drawFats(x1, y2,canvas);
        } else if (tag.equals("水份")) {
            drawmoisture(x1, y2,canvas);
        }
        p.setColor(getResources().getColor(R.color.strock));
        canvas.drawLine(0, y2 + DipTopX.dipTopx(context, 80), l1.getWidth(), y2 + DipTopX.dipTopx(context, 80), p);
        canvas.drawLine(l1.getWidth() / 2, y2 + DipTopX.dipTopx(context, 80), l1.getWidth() / 2, y2 + DipTopX.dipTopx(context, 160), p);

        canvas.drawLine(0, y2 + DipTopX.dipTopx(context, 160), l1.getWidth(), y2 + DipTopX.dipTopx(context, 160), p);

        p.setStrokeWidth(2);
        p.setTextSize(36);
        p.setColor(getResources().getColor(R.color.transparent2));
        canvas.drawText(tag, l1.getWidth() / 4 - DipTopX.dipTopx(context, 12 * tag.length()), y2 + DipTopX.dipTopx(context, 100), p);
        p.setColor(getResources().getColor(R.color.black));
        canvas.drawText("60" + "千克", l1.getWidth() / 4 - tag.length() / 2 * DipTopX.dipTopx(context, 32), y2 + DipTopX.dipTopx(context, 120), p);
        p.setColor(getResources().getColor(R.color.transparent2));
        canvas.drawText("比上次", l1.getWidth() * 3 / 4 - DipTopX.dipTopx(context, 12 * tag.length()), y2 + DipTopX.dipTopx(context, 100), p);

    }

    public void drawPoint() {
        p.setColor(getResources().getColor(R.color.bg_color_press));
        p.setStrokeWidth(4);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        for (int i = 0; i < users.size(); i++) {
//                if(users.get(i).getDate().compareTo())
        }
    }

    public void drawWeights(int startX, int startY,Canvas canvas) {
        p.setColor(getResources().getColor(R.color.red));

        for (int i = 1; i < users.size()+1; i++) {

            canvas.drawCircle(dheight*i,dwidth*i,10,p);
        }
    }

    public void drawFats(int startX, int startY,Canvas canvas) {
        for (int i = 0; i < users.size(); i++) {

        }
    }

    public void drawmoisture(int startX, int startY,Canvas canvas) {
        for (int i = 0; i < users.size(); i++) {

        }
    }

    public void setTag(String s) {
        this.tag = s;
    }

    private void setUsers(List<UserMessage> users) {
        this.users = users;
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
    public void setUser(List<UserMessage> user){
        this.users=users;
    }

}
