package com.abe.dwwd.sporjectone.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/1/7.
 */

public class CustomBzeierHeartView extends View {
    private static final float C = 0.551915024494f;//圆形的控制点

    private Paint mPaint;
    private int mCenterX, mCenterY;

    private PointF mCenter = new PointF(0, 0);//中心点
    private float mCircleRadius = 200;//圆的半径
    private float mDifference = mCircleRadius * C;//圆形的控制点与数据点的差值

    private float[] mData = new float[8];//顺时针记录绘制圆形的四个据点
    private float[] mCtrl = new float[16];//顺时针记录绘制圆形的八个控制点

    private float mDuration = 1000;//变化总时长
    private float mCurrent = 0;//当前已进行市场
    private float mCount = 100;//将市场总共划分多少份
    private float mPiece = mDuration / mCount;//每一份的时长

    public CustomBzeierHeartView(Context context) {
        this(context, null);
    }

    public CustomBzeierHeartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomBzeierHeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        //初始化数据点
        mData[0] = 0;
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;

        //初始化控制点
        mCtrl[0] = mData[0] + mDifference;
        mCtrl[1] = mData[1];


    }
}
