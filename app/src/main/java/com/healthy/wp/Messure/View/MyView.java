package com.healthy.wp.Messure.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.healthy.wp.R;
import com.healthy.wp.UserLogin.model.UserMessage;
public class MyView extends View {
    private Paint p;
    Context context;
    int nScreenWidth, nScreenHeight;
    UserMessage user;

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
    }

    public MyView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);

    }

    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.draw(canvas);
        int x1 = getWidth() / 2;
        int y1 = getHeight() * 2 / 5;
        int innerCircle = DipTopX.dipTopx(context, 60);
        int ciclewidth = DipTopX.dipTopx(context, 10);

        drawMiddleCircle(canvas, x1, y1, innerCircle, ciclewidth);


        int dx = innerCircle + ciclewidth + 10 + 60;
        int dy = innerCircle;

        drawTwoSideCircle(canvas, x1, y1, dx, dy);
    }

    private void drawTwoSideCircle(Canvas canvas, int x1, int y1, int dx, int dy) {
        // TODO Auto-generated method stub
        p.setColor(getResources().getColor(R.color.bg_color_press));
        p.setStrokeWidth(4);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(x1 - dx, y1 + dy, 100, p);

        p.setColor(getResources().getColor(R.color.white));
        p.setTextSize(DipTopX.dipTopx(context, 16));
        p.setStrokeWidth(1);
        canvas.drawText((int)((Double.parseDouble(user.getHeight()))*100)+"", x1 - dx - DipTopX.dipTopx(context, 16), y1 + dy, p);

        p.setColor(getResources().getColor(R.color.white));
        p.setTextSize(DipTopX.dipTopx(context, 16));
        p.setStrokeWidth(1);
        canvas.drawText("cm", x1 - dx - DipTopX.dipTopx(context, 12), y1 + dy + DipTopX.dipTopx(context, 24), p);

        p.setColor(getResources().getColor(R.color.black));
        p.setTextSize(DipTopX.dipTopx(context, 16));
        p.setStrokeWidth(1);
        canvas.drawText("身高", x1 - dx -DipTopX. dipTopx(context, 16), y1 + dy + 100 + DipTopX.dipTopx(context, 32), p);


        p.setColor(getResources().getColor(R.color.bg_color_press));
        p.setStrokeWidth(4);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x1 + dx, y1 + dy, 100, p);


        p.setColor(getResources().getColor(R.color.white));
        p.setTextSize(DipTopX.dipTopx(context, 16));
        p.setStrokeWidth(1);
        canvas.drawText(user.getFat().substring(0,4), x1 + dx - DipTopX.dipTopx(context, 16), y1 + dy, p);

        p.setColor(getResources().getColor(R.color.white));
        p.setTextSize(DipTopX.dipTopx(context, 16));
        p.setStrokeWidth(1);
        canvas.drawText("kg", x1 + dx -DipTopX. dipTopx(context, 12), y1 + dy + DipTopX.dipTopx(context, 16), p);

        p.setColor(getResources().getColor(R.color.black));
        p.setTextSize(DipTopX.dipTopx(context, 16));
        p.setStrokeWidth(1);
        canvas.drawText("脂肪", x1 + dx -DipTopX.dipTopx(context, 16), y1 + dy + 100 + DipTopX.dipTopx(context, 32), p);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        nScreenHeight = heightMeasureSpec;
        nScreenWidth = widthMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void drawMiddleCircle(Canvas canvas, int x1, int y1, int innerCircle, int ciclewidth) {
        p.setColor(getResources().getColor(R.color.bg_color_press));
        p.setStrokeWidth(ciclewidth);

        canvas.drawCircle(x1, y1, innerCircle + ciclewidth, p);


        p.setColor(getResources().getColor(R.color.bg_color_press));
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(x1, y1, innerCircle, p);


        p.setColor(getResources().getColor(R.color.white));
        p.setTextSize(DipTopX.dipTopx(context, 16));
        canvas.drawText(user.getWeight(), x1 - DipTopX.dipTopx(context, 16), y1, p);

        p.setColor(getResources().getColor(R.color.white));
        p.setTextSize(DipTopX.dipTopx(context, 16));
        canvas.drawText("千克", x1 - DipTopX.dipTopx(context, 16), y1 + innerCircle / 2, p);


        p.setColor(getResources().getColor(R.color.black));
        p.setTextSize(DipTopX.dipTopx(context, 16));
        p.setStrokeWidth(1);
        canvas.drawText("体重", x1 - DipTopX.dipTopx(context, 16), y1 + innerCircle + 100 + ciclewidth, p);

    }

    public void drawData(UserMessage user) {
        this.user = user;
        invalidate();
    }


    public void drawNew(){
        invalidate();
    }

}

