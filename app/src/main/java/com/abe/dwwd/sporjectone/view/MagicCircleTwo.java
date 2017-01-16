package com.abe.dwwd.sporjectone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/1/12.
 */

public class MagicCircleTwo extends View {
    private Path mPath;
    private Paint mFillCirclePaint;

    //View的宽度
    private int width;
    //View的高度，这里View应该是正方形，所以宽高都是一样的
    private int height;
    //View 的中心坐标x
    private int centerX;
    //View的中心坐标Y
    private int centerY;

    private float maxLength;
    private float mInterpolatedTime;//插值器时间
    private float stretchDistance;//展开的距离
    private float moveDistance;//移动的距离
    private float cDistanc;
    private float radius;
    private float c;
    private float blackMagic = 0.551915024494f;
    private VPoint p2, p4;
    private HPoint p1, p3;

    public MagicCircleTwo(Context context) {
        this(context, null);
    }

    public MagicCircleTwo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MagicCircleTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mFillCirclePaint = new Paint();
        mFillCirclePaint.setColor(0xFFfe626D);
        mFillCirclePaint.setStyle(Paint.Style.FILL);
        mFillCirclePaint.setStrokeWidth(1);
        mFillCirclePaint.setAntiAlias(true);//抗锯齿
        mPath = new Path();
        p2 = new VPoint();
        p4 = new VPoint();

        p1 = new HPoint();
        p3 = new HPoint();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        centerX = width / 2;
        centerY = height / 2;
        radius = 50;//半径
        c = radius * blackMagic;
        stretchDistance = radius;//展开的距离
        moveDistance = radius * (3 / 5f);//移动的距离
        cDistanc = c * 0.45f;
        maxLength = width - radius - radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        canvas.translate(radius, radius);
        if (mInterpolatedTime >= 0 && mInterpolatedTime <= 0.2) {

        } else if (mInterpolatedTime > 0.2 && mInterpolatedTime <= 0.5){

        }else if (mInterpolatedTime > 0.5 && mInterpolatedTime <= 0.8){

        }else if (mInterpolatedTime > 0.8 && mInterpolatedTime <= 0.9){

        }else if (mInterpolatedTime > 0.9 && mInterpolatedTime <= 1){

        }

        float offset = maxLength * (mInterpolatedTime - 0.2f);

    }

    private void model0(){
        p1.setY(radius);
        p3.setY(-radius);
        p3.x = p1.x = 0;
        p3.left.x = p1.left.x = -c;
        p3.right.x = p1.right.x = c;

        p2.setX(radius);
        p4.setX(-radius);
        p2.y = p4.y = 0;
        p2.bottom.y = p4.bottom.y = c;
    }

    private void model1(float time){
        model0();

        p2.setX(radius + stretchDistance * time * 4);
    }

    private void model2(float time){
        model1(0.2f);

        time = (time - 0.2f) * (10f / 3);
        p1.adjustAllX(stretchDistance / 2 * time);
        p3.adjustAllX(stretchDistance/2 *time);

        p2.adjustY(cDistanc * time);
        p4.adjustY(cDistanc * time);

    }

    private void model3(float time){
        model2(0.5f);
        time = (time - 0.5f) * (10f / 3);
        p1.adjustAllX(stretchDistance / 2 * time);
        p3.adjustAllX(stretchDistance / 2 * time);
        p2.adjustY(-cDistanc * time);
        p4.adjustY(-cDistanc * time);

        p4.adjustAllX(stretchDistance / 2 * time);
    }

    private void model4(float time){
        model1(0.8f);

        time = (time - 0.8f) * 10;
        p4.adjustAllX(stretchDistance / 2 * time);
    }


    class VPoint {
        public float x;
        public float y;
        public PointF top = new PointF();
        public PointF bottom = new PointF();

        public void setX(float x) {
            this.x = x;
            top.x = x;
            bottom.x = x;
        }

        public void adjustY(float offset) {
            top.y -= offset;
            bottom.y += offset;
        }

        public void adjustAllX(float offset) {
            this.x += offset;
            top.x += offset;
            bottom.x += offset;
        }
    }

    class HPoint {
        public float x;
        public float y;
        public PointF left = new PointF();
        public PointF right = new PointF();

        public void setY(float y) {
            this.y = y;
            left.y = y;
            right.y = y;
        }

        public void adjustAllX(float offset) {
            this.x += offset;
            left.x += offset;
            right.x += offset;
        }
    }
}
