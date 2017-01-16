package com.abe.dwwd.sporjectone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/1/6.
 * 绘制文字方法
 * //第一类  只能在指定文本极限位置（基线x默认在字符串左侧，基线y默认在字符串下方）
 *
 * public void drawText(String text,float x,float y,Paint paint);
 * public void drawText(String text,int start,int end,float x,float y,Paint paint);
 * public void drawText(CharSequence text,int start,int end,float x,float y,Paint paint);
 * public void drawText(char[] text,int index,int count,float x,float y,Paint paint);
 *
 * //第二类 可以分别指定每个文字的位置
 * public void drawPostText(String text,float[] pos,Paint paint)
 * public void drawPostText(char[] text,int index,int count,float[] pos,Paint);
 *
 * //第三类 指定一个路径，根据路径绘制文字
 * public drawTextOnPath(String text,Path path,float hOffsef,float voffset,Paint paint);
 * public drawTextOnPath(char[] text,int index,int count,Path path,float hOffset,float vOffset,Paint paint);
 *
 *
 * Paint文本相关常用的方法
 * setColor,setARGB,setAlpha
 * setTextSize
 * setTypeface
 * setStyle FILL,STROKE,FILL_AND_STROKE
 * setTestAlign 左对齐（LEFT),居中（CENTER），右对齐（RIGHT）
 * measureText 测量文字大小（注意，请在设置完文本各项参数后条用）
 *
 */

public class CustomDrawText extends View {
    public CustomDrawText(Context context) {
        super(context);
    }

    public CustomDrawText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
//        textPaint.setTypeface(Typeface.SERIF);
        textPaint.setTextSize(50);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        String str = "HELL";
        // 第一类方法：参数分别为 （文本，基线x，基线y，画笔）
//        canvas.drawText(str,200,500,textPaint);
        //第二类方法：
     /*   canvas.drawPosText(str,new float[]{
                100,100,
                200,200,
                300,300,
                400,400
        },textPaint);*/

        //第三类(drawTextOnPath)
    }
}
