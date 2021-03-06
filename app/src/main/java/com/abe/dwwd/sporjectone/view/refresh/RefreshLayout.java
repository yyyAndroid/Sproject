package com.abe.dwwd.sporjectone.view.refresh;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.abe.dwwd.sporjectone.utils.LogUtils;

public class RefreshLayout extends FrameLayout {

    private static final float OFFSET_RADIO = 2.0f;

    private View mChildView;//子控件
    private View mScrollableView;//
    private BaseRefreshHeaderView mHeaderView;

    private float mHeaderHeight;//顶部高度
    private float mTouchY;//
    private int mTouchSlop;

    private boolean mIsOverlay;//是否覆盖
    private int mMaxOffset;//最大偏移

    private boolean mIsRefreshing;//是否在刷新

    private OnRefreshListener mOnRefreshListener;//刷新的回调

    public RefreshLayout(Context context) {
        this(context, null, 0);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHeaderView = new DefaultRefreshHeaderView(getContext());

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        /**
         *
         getScaledTouchSlop是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件。如果小于这个距离就不触发移动控件，如viewpager
         就是用这个距离来判断用户是否翻页
         */
    }

    /**
     * 该View依赖到窗体时调用
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (getChildCount() > 1) {//只能有一个View
            throw new RuntimeException("can only have one child view");
        }

        mChildView = getChildAt(0);
        if (mChildView == null) {//必须有一个子控件
            throw new RuntimeException("must be have one child view");
        }

        if (isScrollableView(mChildView)) {//是否为可以滚动的view
            mScrollableView = mChildView;
        } else {
            mScrollableView = findScrollableChildView(mChildView);
        }
        if (mScrollableView == null) {
            mScrollableView = mChildView;
        }

        initHeaderView();
    }

    /**
     * 初始化刷新的顶部视图
     */
    private void initHeaderView() {
        // 初始化配置
        HeaderConfig headerConfig = mHeaderView.getConfig();
        mIsOverlay = headerConfig.isOverlay;//是否
        mMaxOffset = headerConfig.maxOffset;//headerView的华东最大高度
        // 提前测量刷新视图的宽高
        measureView(mHeaderView);
        int height = mHeaderView.getMeasuredHeight();
        if (height > 0) {
            mHeaderHeight = height;
        } else {
            throw new RuntimeException("the height of the header view is 0!");
        }
        // 设置layout params
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, (int) mHeaderHeight);
        layoutParams.gravity = Gravity.TOP;
        mHeaderView.setLayoutParams(layoutParams);

        ViewCompat.setTranslationY(mHeaderView, -mHeaderHeight);

        addView(mHeaderView);
    }

    /**
     * 测量视图的宽高
     * @param view
     */
    private void measureView(View view) {
        int w = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }

    /**
     * 是否是可滚动的View
     * @return
     */
    private boolean isScrollableView(View view) {
        return view instanceof AbsListView || view instanceof ScrollView
                || view instanceof RecyclerView;
    }

    /**
     * 递归超找可以滚动的子View
     * @param root
     * @return
     */
    private View findScrollableChildView(View root) {
        if (root == null) {
            return null;
        }

        if (root instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) root;
            View view = null;
            for (int i = 0; i < group.getChildCount(); i++) {
                view = group.getChildAt(i);
                if (isScrollableView(view)) {
                    return view;
                } else {
                    view = findScrollableChildView(view);
                    if (view != null) {
                        return view;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = ev.getY() - mTouchY;
                if (!mIsRefreshing && dy > mTouchSlop && !canChildScrollUp()) {//没有正在刷新&&在可触摸范围内&&是否到达顶部
                    boolean enablePull = false;
                    if (mOnRefreshListener != null) {
                        enablePull = mOnRefreshListener.enableRefresh();//对触摸时间进行拦截
                    }
                    if (enablePull) {
                        mHeaderView.onBegin();
                    }
                    return enablePull;
                }
                break;
            default:
                // nothing to do
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 能否继续往上滑动（是否到达顶部）
     */
    public boolean canChildScrollUp() {
        if (Build.VERSION.SDK_INT < 14) {
            if (mScrollableView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mScrollableView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mScrollableView, -1) || mScrollableView.getScrollY() > 0;
            }
        } else {
            /**
             * 判断控件是否能在垂直向上方向滑动，target是对象，-1是向上的意思。
             * Negative to check scrolling up, positive to check scrolling down
             */
            return ViewCompat.canScrollVertically(mScrollableView, -1) ;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dy = e.getY() - mTouchY;
                dy = Math.max(0, dy);
                // 按照比例缩减滑动的有效距离
                float offsetY = dy / OFFSET_RADIO;
                offsetY = Math.min(offsetY, mMaxOffset);
                // 下拉刷新视图的可见度
                float fraction = offsetY / mHeaderHeight;
                fraction = fraction < 1 ? fraction : 1;

                // 逐渐显示下拉刷新的视图
                ViewCompat.setTranslationY(mHeaderView, (int) (offsetY - mHeaderHeight));

                // 如果不是覆盖模式,则将列表视图向下平移以留出空位来显示下拉刷新的视图
                if (!mIsOverlay) {
                    LayoutParams lp = (LayoutParams) mChildView.getLayoutParams();
                    lp.topMargin = (int) offsetY;
                    mChildView.requestLayout();
                }
                if (offsetY >= mHeaderHeight) {
                    mHeaderView.onRelease();
                } else {
                    mHeaderView.onPull(fraction);
                }
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP://触摸抬起
                if (mIsOverlay) {//是否为覆盖模式
                    /**
                     * ViewCompat.getTranslationY()：相对于View.getTop()的位置
                     */
                    if (ViewCompat.getTranslationY(mHeaderView) >= 0) {
                        viewAnimateTranslationY(mHeaderView, 0);
                        startRefresh();
                    } else {
                        viewAnimateTranslationY(mHeaderView, -mHeaderHeight);
                    }
                } else {
                    if (ViewCompat.getTranslationY(mHeaderView) >= 0) {
                        viewAnimateTranslationY(mHeaderView, 0, mChildView);
                        startRefresh();
                    } else {
                        viewAnimateTranslationY(mHeaderView, -mHeaderHeight, mChildView);
                    }
                }
                return true;
            default:
                // nothing to do
                break;
        }

        return super.onTouchEvent(e);
    }

    /**
     * 使用动画的方式将视图在 Y 轴上平移
     * @param v
     * @param y
     */
    private void viewAnimateTranslationY(final View v, final float y) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(v);
        viewPropertyAnimatorCompat.setDuration(850);
        viewPropertyAnimatorCompat.setInterpolator(new DecelerateInterpolator());
        viewPropertyAnimatorCompat.translationY(y);
        viewPropertyAnimatorCompat.start();
    }

    /**
     * 使用动画的方式将视图在 Y 轴上平移
     * @param v
     * @param y
     */
    private void viewAnimateTranslationY(final View v, final float y, final View childView) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(v);
        viewPropertyAnimatorCompat.setDuration(850);
        viewPropertyAnimatorCompat.setInterpolator(new DecelerateInterpolator());
        viewPropertyAnimatorCompat.translationY(y);
        viewPropertyAnimatorCompat.start();
        viewPropertyAnimatorCompat.setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(View view) {
                float translationY = ViewCompat.getTranslationY(v);
                LayoutParams lp = (LayoutParams) childView.getLayoutParams();
                lp.topMargin = (int) (mHeaderHeight + translationY);
                childView.requestLayout();
            }
        });
    }

    /**
     * 开始刷新
     */
    private void startRefresh() {
        mIsRefreshing = true;
        mHeaderView.onRefreshing();
        if (mOnRefreshListener != null) {
            mOnRefreshListener.onRefresh();
        }
    }

    /**
     * 是否正在刷新
     * @return
     */
    public boolean isRefreshing() {
        return mIsRefreshing;
    }

    /**
     * 设置刷新状态
     * @param refreshing
     */
    public void setRefreshing(boolean refreshing) {
        if (mIsRefreshing == refreshing) {
            return;
        }
        mIsRefreshing = refreshing;
        if (refreshing) {//正在刷新
            if (mIsOverlay) {
                viewAnimateTranslationY(mHeaderView, 0);
            } else {
                viewAnimateTranslationY(mHeaderView, 0, mChildView);
            }
            mHeaderView.onRefreshing();
        } else {//没有刷新
            if (mIsOverlay) {
                viewAnimateTranslationY(mHeaderView, -mHeaderHeight);
            } else {
                viewAnimateTranslationY(mHeaderView, -mHeaderHeight, mChildView);
            }
            mHeaderView.onComplete();
        }
    }

    /**
     * 自动刷新
     */
    public void autoRefresh() {
        if (mIsRefreshing) {
            return;
        }
        if (mIsOverlay) {
            viewAnimateTranslationY(mHeaderView, 0);
        } else {
            viewAnimateTranslationY(mHeaderView, 0, mChildView);
        }
        startRefresh();
    }

    /**
     * 设置自定义的下拉刷新视图
     * @param headerView
     */
    public void setRefreshHeaderView(BaseRefreshHeaderView headerView) {
        mHeaderView = headerView;
    }

    /**
     * 获取下拉刷新视图
     */
    public BaseRefreshHeaderView getRefreshHeaderView() {
        return mHeaderView;
    }

    /**
     * 设置下拉刷新视图是否为覆盖模式
     * @param isOverlay
     */
    public void setIsOverlay(boolean isOverlay) {
        mIsOverlay = isOverlay;
    }

    /**
     * 设置刷新的监听器
     * @param onRefreshListener
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        mOnRefreshListener = onRefreshListener;
    }
}
