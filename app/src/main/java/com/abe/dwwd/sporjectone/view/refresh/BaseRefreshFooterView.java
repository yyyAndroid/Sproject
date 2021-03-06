package com.abe.dwwd.sporjectone.view.refresh;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * 底部加载更多View基础类
 * 所有的底部加载更多的View必需继承这个类
 */
public abstract class BaseRefreshFooterView extends FrameLayout {

    public BaseRefreshFooterView(Context context) {
        super(context);
    }

    /**
     * 加载中
     */
    public abstract void onLoadMore();

    /**
     * 加载完成
     */
    public abstract void onComplete();
}