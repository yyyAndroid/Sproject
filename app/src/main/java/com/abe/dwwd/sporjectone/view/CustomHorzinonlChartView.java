package com.abe.dwwd.sporjectone.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.abe.dwwd.sporjectone.R;
import com.abe.dwwd.sporjectone.utils.LogUtils;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/1/13.
 */

public class CustomHorzinonlChartView extends View {

    private Paint mChartPaint;
    private int drawColor;
    private int mHeight, mWidth, mChartWidth;
    private int number;
    private static final String TAG = "CustomHorzinonlChartVie";
    private String leftText,rightText;
    private Paint leftPaint,rightPaint;
    private float moveTime;
    public void setNumber(int number) {
        this.number = number;

        if ((number * mWidth / 100.f) > leftPaint.measureText(leftText) + mHeight / 2){
            leftPaint.setColor(Color.WHITE);
        }else{
            leftPaint.setColor(Color.BLACK);
        }

        if ((number * mWidth / 100.f) > mWidth - rightPaint.measureText(rightText) - mHeight / 2){
            rightPaint.setColor(Color.WHITE);
        }else{
            rightPaint.setColor(Color.BLACK);
        }
        invalidate();
    }


    public void setLeftText(String leftText){
        this.leftText = leftText;
        leftPaint.setTextSize(mHeight/2);
        if ((number * mWidth / 100.f) > leftPaint.measureText(leftText) + mHeight / 2){
            leftPaint.setColor(Color.WHITE);
        }else{
            leftPaint.setColor(Color.BLACK);
        }

    }

    public void setRightText(String rightText){
        this.rightText = rightText;
        if ((number * mWidth / 100.f) > mWidth - rightPaint.measureText(rightText) - mHeight / 2){
            rightPaint.setColor(Color.WHITE);
        }else{
            rightPaint.setColor(Color.BLACK);
        }
    }
    public CustomHorzinonlChartView(Context context) {
        this(context, null);
    }

    public CustomHorzinonlChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHorzinonlChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomCharView);
        drawColor = ta.getColor(R.styleable.CustomCharView_drawColor, Color.GREEN);
        ta.recycle();
        initialize();
    }

    private void initialize() {
        mChartPaint = new Paint();
        mChartPaint.setAntiAlias(true);
        leftPaint = new Paint();
        leftPaint.setStyle(Paint.Style.FILL);
        rightPaint = new Paint();
        rightPaint.setStyle(Paint.Style.FILL);

        leftText = "左边文字";
        rightText = "右边文字";
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();

        leftPaint.setTextSize(mHeight/2);
        rightPaint.setTextSize(mHeight/2);

        if ((number * mWidth / 100.f) > leftPaint.measureText(leftText) + (mHeight / 2.0)){
            leftPaint.setColor(Color.WHITE);
        }else{
            leftPaint.setColor(Color.BLACK);
        }

        if ((number * mWidth / 100.f) > mWidth - rightPaint.measureText(rightText) - (mHeight / 2.0)){
            rightPaint.setColor(Color.WHITE);
        }else{
            rightPaint.setColor(Color.BLACK);
        }
    }

    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mChartPaint.reset();
        LogUtils.d("onDraw()");
        mChartPaint.setColor(drawColor);
        mChartPaint.setStyle(Paint.Style.FILL);

        drawBg(canvas);
        drawPlan(canvas);
        drawText(canvas);
    }

    private void drawBg(Canvas canvas) {
        mChartPaint.setColor(Color.GRAY);
        RectF rectFBg = new RectF();
        rectFBg.top = 0;
        rectFBg.bottom = mHeight;
        rectFBg.left = 0;
        rectFBg.right = mWidth;
        canvas.drawRoundRect(rectFBg, mWidth/2, mWidth/2, mChartPaint);

    }

    private void drawPlan(Canvas canvas) {
        RectF rectF = new RectF();
        rectF.top = 0;
        rectF.bottom = mHeight;
        rectF.left = 0;
        rectF.right = (number * mWidth / 100.f) * moveTime;

        mChartPaint.setColor(drawColor);
        canvas.drawRoundRect(rectF, mWidth/2, mWidth/2, mChartPaint);
    }

    /**
     * 通过中心点得到基线
     * 指定文字在中间的位置，绘制文本公式为：
     * float baseLineY = centerY + (fontMetrics.bottom - fontMetrics.top) / 2  - fontMetrics.bottom;
     */
    private void drawText(Canvas canvas) {
        canvas.drawText(leftText,mHeight/2,getBaseLineY(mHeight / 2),leftPaint);
        canvas.drawText(rightText,mWidth - rightPaint.measureText(rightText)-(mHeight/2),getBaseLineY(mHeight / 2),rightPaint);
    }

    private float getBaseLineY(float centerY){
        return centerY + (rightPaint.getFontMetrics().bottom - rightPaint.getFontMetrics().top) / 2 - rightPaint.getFontMetrics().bottom;
    }

    class MoveAnimation extends Animation{
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            moveTime = interpolatedTime;
            invalidate();
        }
    }

    public void startAnimation(){
        mChartPaint.reset();
        moveTime = 0;

        MoveAnimation moveAnimation = new MoveAnimation();
        moveAnimation.setDuration(1000);
        moveAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        startAnimation(moveAnimation);
    }
}
