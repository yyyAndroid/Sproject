package com.abe.dwwd.sporjectone.view.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 头部下拉刷新View基础类
 * 所有的头部下拉刷新的View必需继承这个类
 */
public abstract class BaseRefreshHeaderView extends FrameLayout {

    public BaseRefreshHeaderView(Context context) {
        super(context);
    }

    public BaseRefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void onBegin();//开始

    /**
     * @param fraction 下拉因子
     */
    public abstract void onPull(float fraction);//下拉

    public abstract void onRelease();//释放

    public abstract void onRefreshing();//刷新中

    public abstract void onComplete();//完成

    public abstract HeaderConfig getConfig();
}
