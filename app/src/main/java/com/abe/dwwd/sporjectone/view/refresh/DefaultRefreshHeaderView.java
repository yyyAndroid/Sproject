package com.abe.dwwd.sporjectone.view.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


import com.abe.dwwd.sporjectone.R;
import com.abe.dwwd.sporjectone.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 默认的头部下拉刷新view
 */

/**
 * 头部的下拉刷新动画
 */
public class DefaultRefreshHeaderView extends BaseRefreshHeaderView {
    ImageView sunImg;

    ImageView riderImg;

    ImageView leftWheelImg;

    ImageView rightWheelImg;

    ImageView leftBackImg;

    ImageView rightBackImg;
    private Animation sunRotation;
    private Animation riderShake;
    private Animation wheelRotation;
    private Animation leftBackTranslate;
    private Animation rightBackTranslate;

    private Unbinder unbinder;

    public DefaultRefreshHeaderView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_refresh_header, this);
        sunImg = (ImageView) findViewById(R.id.pull_sun);
        riderImg = (ImageView) findViewById(R.id.pull_rider);
        leftWheelImg = (ImageView) findViewById(R.id.pull_wheel_left);
        rightWheelImg = (ImageView) findViewById(R.id.pull_wheel_right);
        leftBackImg = (ImageView) findViewById(R.id.pull_backImg_left);
        rightBackImg = (ImageView) findViewById(R.id.pull_backImg_right);

        initAnimations();
    }

    private void initAnimations() {
        //太阳旋转动画
        sunRotation = new RotateAnimation(0, 179,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        sunRotation.setInterpolator(new LinearInterpolator());
        sunRotation.setRepeatCount(Animation.INFINITE);
        sunRotation.setDuration(600);

        //起手上下移动动画
        riderShake = new TranslateAnimation(0, 0, 0, 5);
        riderShake.setRepeatMode(Animation.REVERSE);
        riderShake.setRepeatCount(Animation.INFINITE);
        riderShake.setInterpolator(new DecelerateInterpolator());
        riderShake.setDuration(225);

        //车轮转动动画
        wheelRotation = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        wheelRotation.setInterpolator(new LinearInterpolator());
        wheelRotation.setRepeatCount(Animation.INFINITE);
        wheelRotation.setDuration(300);

        //左边背景移动动画
        leftBackTranslate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        leftBackTranslate.setRepeatMode(Animation.RESTART);
        leftBackTranslate.setRepeatCount(Animation.INFINITE);
        leftBackTranslate.setInterpolator(new LinearInterpolator());
        leftBackTranslate.setDuration(2000);

        //右边背景移动动画
        rightBackTranslate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        rightBackTranslate.setRepeatMode(Animation.RESTART);
        rightBackTranslate.setRepeatCount(Animation.INFINITE);
        rightBackTranslate.setInterpolator(new LinearInterpolator());
        rightBackTranslate.setDuration(2000);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }

    @Override
    public HeaderConfig getConfig() {
        HeaderConfig config = new HeaderConfig();
        config.isOverlay = false;
        config.maxOffset = 300;
        return config;
    }

    /**
     * 开始向下滑动的毁掉
     */
    @Override
    public void onBegin() {
    }

    @Override
    public void onPull(float fraction) {

    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onRefreshing() {//刷新中
        sunImg.startAnimation(sunRotation);
        riderImg.startAnimation(riderShake);
        leftWheelImg.startAnimation(wheelRotation);
        rightWheelImg.startAnimation(wheelRotation);
        leftBackImg.startAnimation(leftBackTranslate);
        rightBackImg.startAnimation(rightBackTranslate);
    }

    @Override
    public void onComplete() {//刷新完成
        sunImg.clearAnimation();
        riderImg.clearAnimation();
        leftWheelImg.clearAnimation();
        rightWheelImg.clearAnimation();
        leftBackImg.clearAnimation();
        rightBackImg.clearAnimation();
    }
}