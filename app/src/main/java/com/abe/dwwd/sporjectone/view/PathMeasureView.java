package com.abe.dwwd.sporjectone.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by abe on 2017/3/22.
 */

class PathMeasureView extends View {
    private Path mPath;
    private Paint mPaint;
    private PathMeasure mPathMeasre;
    private float mAnimatorValue;
    private Path mDst;
    private float mLength;

    public PathMeasureView(Context context) {
        this(context,null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPathMeasre = new PathMeasure();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
        mPath.addCircle(400,400,100,Path.Direction.CW);//圆心x坐标 ，圆心y坐标 ，半径，方向CW：顺时钟，CCW逆时针
        mPathMeasre.setPath(mPath,true);
        mLength = mPathMeasre.getLength();
        mDst = new Path();

        final ValueAnimator valueAnimator = new ValueAnimator().ofFloat(0,1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDst.reset();
        //硬件加速bug
        mDst.lineTo(0,0);
        float stop = mLength * mAnimatorValue;
        float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mLength));
        mPathMeasre.getSegment(start,stop,mDst,true);
        canvas.drawPath(mDst,mPaint);
    }
}
