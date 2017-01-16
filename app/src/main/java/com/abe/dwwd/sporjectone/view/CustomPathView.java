package com.abe.dwwd.sporjectone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/1/6.
 * Path常用方法列表
 * moveTo 移动起点  移动下一次操作的起点
 * setLastPoint 设置终点  重置当前path中最后一个位置，如果在绘制之前调用，
 * 效果和moveTo相同
 * lineTo 添加上一个点到当前点之间的直线到path
 * close 闭合路径 链接第一个点链接到最后偶一个点，星辰一个闭行区域
 * 添加内容 addRect，addRoundRect,addOval,addCircle,
 * addPath,addArc,arcTo
 * isEmpty 是否为空
 * isRect 判断是否是一个矩形
 * set 替换路径   用新的路径途欢到当前路径所有内容
 * offset 对当前路径之前的操作进行偏移（不会影响之后的操作）
 * quadTo,cubicTo  分别为二次和三次贝塞尔曲线的方法
 * rXxx方法 rMoveTo rLineTo,rQuadTo ,rCubicTo //不带r的方法是基于远点左边系偏移，daiR是基于当前
 * 当前坐标系偏移（偏移量）
 * 填充模式 setFillType,fetFillType,isInverseFillType,toggleInverseFillType//设置获取，判断，切换填充模式
 * 提示方法 incReserve 提示Path还有多少个点等待加入（这个方法貌似会让Path优化存储结构）
 * op 布尔操作 API19  对两个Path进行布尔运算（既取交集并集）
 * computeBounds 计算边界 计算Path的边界
 * 重置路径 rest，rewind  清除Path中的内容
 * reset  不败刘内部数据结构，但会保留FillType
 * transform 矩阵变换
 */

public class CustomPathView extends View {
    public CustomPathView(Context context) {
        this(context, null);
    }

    public CustomPathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathFour(canvas);
    }

    private void pathOne(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);//变现宽度
        canvas.drawColor(Color.GREEN);
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        Path path = new Path();
        path.lineTo(200, 200);
        path.lineTo(200, 0);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    private void pathTwo(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);//变现宽度
        canvas.drawColor(Color.GREEN);
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);

        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
        path.setLastPoint(-300, 300);
        canvas.drawPath(path, mPaint);
        //第二类 （基本图形）
        //圆形 public void addCircle(float x,float y,float radius,Path.Direction dir)
        //椭圆 public void addOval(RectF oval,Paht.Direction dir)
        //矩形 public void addRect(float left,float top,float right,float bottom,Paht.Direction dir)
        //圆角矩形 public void addRoundRect (RectF rect,float[] radii,Path,Direction dir)
        //public void addRoundRect(RectF rect,float rx,float ry,Paht,Direction dir)

    }

    private void pathThree(Canvas canvas) {
        //第二类 Path public void addPath(Path src) public void addPath(Path src,float dx,float dy)
        //public void addPath(Path src,float dx,float dy)
        //public void addPath(Path src,Matrix matrix)
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);//变现宽度
        canvas.drawColor(Color.GREEN);

        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        canvas.scale(1, -1);

        Path path = new Path();
        Path src = new Path();
/**
 * CW 顺时针
 * CCW 逆时针
 */
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);

        path.addPath(src, 0, 200);

        canvas.drawPath(path, mPaint);
    }

    /**
     * 第三类 addArc和arcTo
     * public void addArc(RectF oval,float startAngle,float sweepAngle)
     * <p>
     * public void addTo(RectF oval,float startAngle,float sweepAngle);
     * public void addTo(RectF oval,float startAngle,float sweepAngle,boolean forceMoveTo);
     * forceMoveTo： （true： 将最后一个点移动到圆弧起点，既不连接最后一个点与圆弧起点
     *              false:不以偶定，二回链接自由一个点与圆弧起点）
     *
     *
     *
     *              isEmpty:判断path是否包含内容
     *              isRect:判断是否是一个矩形，如果是一个矩形的话，会将矩形的信息存放在参数rect中
     *
     *
     *              offset 对Path进行评议
     * @param canvas
     */
    private void pathFour(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);//变现宽度
        canvas.drawColor(Color.GREEN);

        Path path = new Path();
//        Path src = new Path();
//        src.addArc(new RectF(0,0,400,400),0,290);

        path.lineTo(100, 100);

        path.moveTo(300,300);
        RectF oval = new RectF(0, 0, 300, 300);//float精度的矩形
        path.arcTo(oval, 0, 270,true);

        mPaint.setColor(Color.BLACK);
        path.lineTo(500,0);

        canvas.drawPath(path, mPaint);

    }

}
