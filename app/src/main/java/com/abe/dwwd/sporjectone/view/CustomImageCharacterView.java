package com.abe.dwwd.sporjectone.view;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/1/3.
 * 绘制图片  使用Picture是要关闭硬件加速
 * drawPicture(矢量图)
 * drawBitmap(位图)
 * public int getWidth()//获取宽度
 * public int getHeight()//获取高度
 * public Canvas beginRecordin(int width,int height)//开始录制返回一个Canvas，在
 * Canvas中素有的绘制都会存储在Picture
 * public void endRecoding()//结束录制
 * public void draw(Canvas canvas)将Picture中内容会知道Canvas中
 * public static Picture createFromStream(InputStream stream)//通过输入流常见一个Picture
 * public static Picture writeToStream(OutputStream stream)//将Picture中内容写出到
 */

public class CustomImageCharacterView extends View {
    //1.创建Picture
    private Picture mPicture = new Picture();
    private static final String TAG = "CustomImageCharacterVie";
    private static final String TAG_NAME = "图片文字控件";
    private Context mContext;
    public CustomImageCharacterView(Context context) {
        this(context, null);
        Log.e(TAG, TAG_NAME + "this(context, null);");
    }

    public CustomImageCharacterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.e(TAG, TAG_NAME + "this(context, null,0);");
    }

    public CustomImageCharacterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        Log.e(TAG, TAG_NAME + "super(context, attrs,defStyleAttr);");
        recording();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, TAG_NAME + "mPicture.getHeight():" + mPicture.getHeight() + "  mPicture.getWidth():" + mPicture.getWidth());
//        drawPictureMethod(canvas);
        drawBitmapMethod(canvas);
    }

    /**
     * 注注：需要关闭硬件加速
     * 注注：需要关闭硬件加速
     * 注注：需要关闭硬件加速
     * 注注：需要关闭硬件加速
     * 注注：需要关闭硬件加速
     * 注注：需要关闭硬件加速
     * 注注：需要关闭硬件加速
     * @param canvas
     */
    private void drawPictureMethod(Canvas canvas) {
        //第一种使用方式   对Canvas有影响，绘制完成后会影响
        mPicture.draw(canvas);
        //第二种使用方式  对Canvas操作无影响
        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 200));
        //第三种使用方式
        PictureDrawable drawable = new PictureDrawable(mPicture);
        drawable.setBounds(0, 0, 250, mPicture.getHeight());
        drawable.draw(canvas);
        Log.e(TAG, TAG_NAME + "onDraw(Canvas canvas)");
    }

    /**
     * 在Canvas中绘制图片
     * @param canvas
     */
    private void drawBitmapMethod(Canvas canvas){
        //第一种
//        canvas.drawBitmap(getBitmap(),new Matrix(),new Paint());
        //第二种
        canvas.drawBitmap(getBitmap(),200,500,new Paint());
        //第三种
        canvas.drawBitmap(getBitmap(),new Rect(0,0,getBitmap().getWidth()/2,getBitmap().getHeight()/2),
                new RectF(0,0,200,100),null);
    }

    private Bitmap getBitmap(){
        //资源文件 raw
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), android.R.mipmap.sym_def_app_icon);
        //assets 资源文件
      /*  Bitmap bitmap1 = null;
        try {
            InputStream is = mContext.getAssets().open("bitmap.png");
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //内存卡读取文件
        Bitmap bitmap2 = BitmapFactory.decodeFile("/sdcard/bitmap.png");*/
        return bitmap;
    }
    //2.录制内容方法
    private void recording() {
        //开始录制

        Canvas canvas = mPicture.beginRecording(300, 300);
        //创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        //在Canvas中的具体操作
        canvas.translate(250, 250);
        //绘制一个圆
        canvas.drawCircle(0, 0, 100, paint);

        mPicture.endRecording();//结束录制
    }
}
