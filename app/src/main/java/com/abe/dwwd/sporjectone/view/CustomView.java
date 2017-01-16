package com.abe.dwwd.sporjectone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/12/27.
 * 画布操作  translate 平移
 * scale   缩放
 * retate 旋转  void reate(float degrees) void reate(float degrees ,float px,float py)
 * skew  错切  void skew(float sx,float sy)
 */

/**
 * 快照和回滚 相关API
 * save 把当前的画布的状态进行保存，然后放入特定的栈中
 * saveLayerXxx 新建一个图层，并放入一个特定的栈中
 * restore 把栈中最顶层的画布状态取出来，并按照这个状态恢复当前的画布
 * restoreToCount 弹出指定位置及其以上所有的状态，并按照指定位置的状态进行恢复
 * getSaveCount 获取栈中内容的数量（既保存次数）
 *
 *
 * SaveFlags :
 * int ALL_SAVE_FLAG :默认，保存全部状态
 * int CLIP_SAVE_FLAG:保存剪辑区
 * int CLIP_TO_LAYER_SAVE_FLAG :剪辑区作为图层保存
 * int FULL_COLOR_LAYER_SAVE_FLAG:保存图层的全部色彩通道
 */

public class CustomView extends View {
    Paint mPaint = new Paint();

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        canvas.translate(getMeasuredWidth()/2,getHeight()/2);//移动到屏幕中心
        mPaint.setColor(Color.RED);
        canvas.drawLine(0,-700,0,700,mPaint);
        canvas.drawLine(0,-700,-50,-640,mPaint);
        canvas.drawLine(0,-700,50,-640,mPaint);

        canvas.drawLine(-600,0,350,0,mPaint);
        canvas.drawLine(350,0,290,-50,mPaint);
        canvas.drawLine(350,0,290,50,mPaint);
        mPaint.setColor(Color.BLUE);
        RectF rectF = new RectF(0,-200,200,0);
        canvas.drawRect(rectF,mPaint);//矩形
        canvas.skew(0,0);//错切
        canvas.skew(1,0);

        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rectF,mPaint);
    }
}
