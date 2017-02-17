package com.abe.dwwd.sporjectone.demo.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2017/2/17.
 *
 * 完美解决枚举占用内存大问题
 */

//构建定义注解
public class UserInter {
    public static final int childe = 0x1;
    public static final int man = 0x2;
    public static final int girl = 0x3;
    public static final int other = 0x4;

    /**
     * Android中 新引入的替代枚举的注解有 IntDef和StringDef ，他唯一的区别是一个int类型，一个是string类型，
     * 下面以 IntDef为例讲解如何使用
     */

    /**
     * @Retention 表示注解类型的存货时长 和  @interface定义一个注解
     */

    /**
     * 资源类型注解
     *
     * AnimRes  动画
     * AnimatorRes anmator资源类型
     * AnyRes    任何类型资源
     * ArrayRes  数组资源类型
     * AttrRes    属性资源类型
     * BoolRes    bool类型资源类型
     * ColorRes    颜色类型资源
     * DimenRes   长度资源类型
     * DrawableRes  图片资源类型
     * IdRes    资源Id
     * InterpolatorRes   动画插值器
     * LayoutRes   layout资源
     * MenuRes     menu资源
     * RawRes      raw资源
     * StringRes   字符串资源
     * StyleRes  style资源
     * StyleableRes   Styleable资源类型
     * TransitionRes   transition资源类型
     * XmlRes   xml资源
     */

    @IntDef({childe,man,girl})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UserInters{}

    public int userType;

    @UserInters
    public int getUserType(){
        return userType;
    }

    public void setUserType(@UserInters int userType){
        this.userType = userType;
    }
}
