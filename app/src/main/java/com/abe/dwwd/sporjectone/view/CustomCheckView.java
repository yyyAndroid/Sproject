package com.abe.dwwd.sporjectone.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.abe.dwwd.sporjectone.R;

/**
 * Created by Administrator on 2017/1/5.
 */

public class CustomCheckView extends View {
    private static final int NONE = 0;//动画状态-没有
    private static final int CHECK = 1;//动画状态-开启
    private static final int UNCHECK = 2;//动画状态-结束
    private Context mContext;

    private Handler mHandler;
    private Paint mPaint;

    private int mWidth,mHeight;

    private int animCurrentPage = -1;//当前页码
    private int animDuration = 500;//动画时长
    private int animState = NONE;//动画状态
    private int animMaxPage = 13;//最大页码

    public boolean isCheck;//是否只选中状态
    public CustomCheckView(Context context) {
        this(context,null);
    }

    public CustomCheckView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomCheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initialization();
    }

    /**
     * 初始化
     */
    private Bitmap okBitmap;
    private void initialization() {
        mPaint = new Paint();
        mPaint.setColor(mContext.getResources().getColor(android.R.color.holo_green_light));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(false);//抗锯齿
        okBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.checkmark);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //小于最大页码时
                if (animCurrentPage < animMaxPage && animCurrentPage >= 0){
                    invalidate();
                    if (animState == NONE){
                        return;
                    }
                    if (animState == CHECK){
                        animCurrentPage++;
                    }else if (animState == UNCHECK){
                        animCurrentPage--;
                    }
                    this.sendEmptyMessageDelayed(0,animDuration / animMaxPage);
                }else{//超过最大页码
                    if (isCheck){
                        animCurrentPage = animMaxPage - 1;
                    }else{
                        animCurrentPage = -1;
                    }
                    invalidate();
                    animState = NONE;
                }
            }
        };

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动坐标系到画布中央
        canvas.translate(mWidth / 2,mHeight / 2);

        //绘制背景圆形
        canvas.drawCircle(0,0,240,mPaint);

        //得出图像边长
        int sideLength = okBitmap.getHeight();

        //得到图像选取和实际绘制位置  图像边长
        Rect src = new Rect(sideLength * animCurrentPage,0,sideLength * (animCurrentPage + 1),sideLength);
        Rect dst = new Rect(-200,-200,200,200);

        canvas.drawBitmap(okBitmap,src,dst,null);
    }

    /**
     * 选择
     */
    public void check(){
        if (animState != NONE || (isCheck))
            return;
        animState = CHECK;
        animCurrentPage = 0;
        isCheck = true;//已经为选中
        mHandler.sendEmptyMessageDelayed(0,animDuration / animMaxPage);
    }
    /**
     * 取消选择
     */
    public void unCheck(){
        if (animState != NONE || (!isCheck))
            return;
        animState = UNCHECK;
        animCurrentPage = animMaxPage - 1;        isCheck = false;

        mHandler.sendEmptyMessageDelayed(0,animDuration / animMaxPage);
    }
    /**
     * 设置动画时长
     * @param animDuration
     */
    public void setAnimDuration(int animDuration){
        if (animDuration <= 0){
            return;
        }
        this.animDuration = animDuration;
    }

    /**
     * 设置背景圆形颜色
     * @param color
     */
    public void setBackgroundColor(int color){
        mPaint.setColor(color);
    }
}
