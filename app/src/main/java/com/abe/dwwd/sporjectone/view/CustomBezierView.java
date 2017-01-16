package com.abe.dwwd.sporjectone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/1/7.
 * 贝赛尔曲线
 * <p>
 * 一阶贝塞尔曲线 lineTo
 * 二阶贝塞尔曲线  quadTo
 * 三阶贝塞尔曲线 cubicTo
 */

public class CustomBezierView extends View {
    private Paint mPaint;
    private int centerX,centerY;
    private static final String TAG = "CustomBezierView";
    private static final String TAG_NAME = "贝塞尔曲线";
    private PointF start,end,control;
    public CustomBezierView(Context context) {
        this(context,null);
    }

    public CustomBezierView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        start = new PointF(0,0);
        end = new PointF(0,0);
        control = new PointF(0,0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG,TAG_NAME+"protected void onSizeChanged(int w, int h, int oldw, int oldh)");
        centerX = w/2;
        centerY = h/2;

        //初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;

        end.x = centerX + 200;
        end.y = centerY;

        control.y = centerX;
        control.y = centerY - 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);

        //绘制数据点和控制点
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control.x,control.y,mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(control.x,control.y,end.x,end.y,mPaint);

        //绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);

        canvas.drawPath(path,mPaint);
    }


}
