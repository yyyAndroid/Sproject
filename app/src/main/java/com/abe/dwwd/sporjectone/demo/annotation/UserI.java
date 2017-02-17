package com.abe.dwwd.sporjectone.demo.annotation;

import android.support.annotation.CallSuper;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import com.abe.dwwd.sporjectone.R;

/**
 * Created by Administrator on 2017/2/17.
 */

public class UserI {
    public static int child = 0x1;
    public static int man = 0x2;
    public static int girl = 0x3;

    private int userType;

    @UserInter.UserInters
    public int getUserType(){
        return userType;
    }

    public void setUserType(@UserInter.UserInters int userType){
        this.userType = userType;
    }

    /**
     * @StringRes注解规定传入的类型为 字符串资源类型
     * @param str
     */
    public void setDemoString(@StringRes int str){

    }

    /**
     * @NonNull 注解规定传入的类型 不能为空
     * @param str
     */
    public void setDemoNonNull(@NonNull String str){

    }

    /**
     *
     * @param str
     */
    public void setDemoNullable(@Nullable String str){

    }


    /**
     * Threading注解  线程注解
     * thread注解是帮助我们指定方法在特定线程中执行处理的，如果和指定的线程不一致，就抛出异常；Threading注解类型
     *
     * @UiThread:UI线程
     * @MainThread:主线程
     * @WorkerThread:子线程
     * @BinderThread:绑定线程
     */
        @UiThread
        public void setDemoUiThread(){

        }


    /**
     *  Value Constraints注解
     *  值限制注解
     *  @size //定义值的大小，可选择最小和最大值使用
     *  @IntRange//用来指定int类型范围的注解
     *  @FloatRange//用来指定float类型范围的注解
     */

    public void setDemoSize(@Size(min = 0,max = 2)String s){}
    public void setDemoInt(@IntRange(from = 0,to = 2)int i){}
    public void setDemoFloat(@FloatRange(from =  0, to = 3)float demoFloat){}

    /**
     * 枚举相对比其他方案来说占用内容相对恨大，而曾被google被列为 不建议使用
     * 既然枚举有他的缺陷，那如何完美解决这样的需求呢，以下完美实现-就是Android中用注解来替换Java中的枚举
     */

    /**
     * CallSuper注解
     * @callSuper注解只要是用来强调在覆盖父类方法时，需要实现父类的方法，及时调用对应的super
     *
     */

    class CallSuperT{
        @CallSuper
        protected  void init(){

        }

        public int returI(){
            return 1;
        }
        class  CallSub extends CallSuperT{
            @Override
            protected void init() {

            }
        }
    }
    /**
     * CheckResult注解
     *
     * 假设你定义了一个方法返回一个值，你期望调用者用这个值做些事情，那么你可以使用@CheckResult 注解这个方法，强制用户定义一个相应的返回值
     * ，使用它
     */

    public void CheckResultDemo(){
        CallSuperT callSuperT = new CallSuperT();
        callSuperT.returI();
    }

    /**
     * 注解的作用：
     * 1.提高我们的开发效率
     * 2.更早的发现程序的问题或者错误
     * 3.更好的增加代码的描述能力
     * 4.更加有利于我们的一些规范约束
     */
}
