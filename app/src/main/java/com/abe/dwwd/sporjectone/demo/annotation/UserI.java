package com.abe.dwwd.sporjectone.demo.annotation;

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

    public static enum UserEnum{
        childe,
        man,
        gril;
    }

    /**
     * 枚举相对比其他方案来说占用内容相对恨大，而曾被google被列为 不建议使用
     * 既然枚举有他的缺陷，那如何完美解决这样的需求呢，以下完美实现-就是Android中用注解来替换Java中的枚举
     */
}
