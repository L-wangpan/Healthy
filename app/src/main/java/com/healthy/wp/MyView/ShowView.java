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
    int dwidth, dheight;
    int x1, y1, x2, y2, x, y;
    Date date;

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
        y2 = l1.getHeight()*2 / 5;
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
        canvas.drawLine(x1, DipTopX.dipTopx(context, 30), x1, l1.getHeight() *2/5 , p);
        canvas.drawLine(x1,l1. getHeight()*2 / 5, l1.getWidth() - DipTopX.dipTopx(context, 40), y2, p);

        canvas.drawLine(x1, y2 - dheight, x1 + DipTopX.dipTopx(context, 10), y2 - dheight, p);
        canvas.drawLine(x1, y2 - 2 * dheight, x1 + DipTopX.dipTopx(context, 10), y2 - 2 * dheight, p);
        canvas.drawLine(x1, y2 - 3 * dheight, x1 + DipTopX.dipTopx(context, 10), y2 - 3 * dheight, p);
        canvas.drawLine(x1, y2 - 4 * dheight, x1 + DipTopX.dipTopx(context, 10), y2 - 4 * dheight, p);

        canvas.drawLine(x1 + dwidth, y2, x1 + dwidth, y2 - DipTopX.dipTopx(context, 10), p);
        canvas.drawLine(x1 + 2 * dwidth, y2, x1 + 2 * dwidth, y2 - DipTopX.dipTopx(context, 10), p);
        canvas.drawLine(x1 + 3 * dwidth, y2, x1 + 3 * dwidth, y2 - DipTopX.dipTopx(context, 10), p);
        canvas.drawLine(x1 + 4 * dwidth, y2, x1 + 4 * dwidth, y2 - DipTopX.dipTopx(context, 10), p);

        p.setTextSize(DipTopX.dipTopx(context, 8));
        canvas.drawText("25", DipTopX.dipTopx(context, 20), y2 - dheight + DipTopX.dipTopx(context, 4), p);
        canvas.drawText("55", DipTopX.dipTopx(context, 20), y2 - 2 * dheight + DipTopX.dipTopx(context, 4), p);
        canvas.drawText("85", DipTopX.dipTopx(context, 20), y2 - 3 * dheight + DipTopX.dipTopx(context, 4), p);
        canvas.drawText("115", DipTopX.dipTopx(context, 20), y2 - 4 * dheight + DipTopX.dipTopx(context, 4), p);

        p.setColor(getResources().getColor(R.color.chengse));
        canvas.drawText(String.valueOf(date.getMonth() + 1) + "月", x1 - DipTopX.dipTopx(context, 4), y2 + DipTopX.dipTopx(context, 16), p);
        canvas.drawText(String.valueOf(date.getDay()+1) + "日", x1 + dwidth - DipTopX.dipTopx(context, 4), y2 + DipTopX.dipTopx(context, 16), p);
        canvas.drawText(String.valueOf(date.getDay()+8)+"日", x1 + 2 * dwidth - DipTopX.dipTopx(context, 4), y2 + DipTopX.dipTopx(context, 16), p);
        canvas.drawText(String.valueOf(date.getDay()+15)+"日", x1 + 3 * dwidth - DipTopX.dipTopx(context, 4), y2 + DipTopX.dipTopx(context, 16), p);
        canvas.drawText(String.valueOf(date.getDay()+22)+"日", x1 + 4 * dwidth - DipTopX.dipTopx(context, 4), y2 + DipTopX.dipTopx(context, 16), p);

        p.setColor(getResources().getColor(R.color.strock));
        canvas.drawLine(0,y2 + DipTopX.dipTopx(context, 80),l1.getWidth(),y2+DipTopX.dipTopx(context,80),p);
        canvas.drawLine(l1.getWidth()/2,y2+DipTopX.dipTopx(context,80),l1.getWidth()/2,y2+DipTopX.dipTopx(context,160),p);

        canvas.drawLine(0,y2 + DipTopX.dipTopx(context, 160),l1.getWidth(),y2+DipTopX.dipTopx(context,160),p);
        p.setColor(getResources().getColor(R.color.black));

        p.setStrokeWidth(2);
        p.setTextSize(64);
        canvas.drawText("重量",l1.getWidth()/4-DipTopX.dipTopx(context,24),y2+DipTopX.dipTopx(context,100),p);
        canvas.drawText("60"+"千克",l1.getWidth()/4-DipTopX.dipTopx(context,32),y2+DipTopX.dipTopx(context,120),p);
    }


    private void setUsers() {
        invalidate();
    }


}
