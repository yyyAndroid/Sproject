package com.abe.dwwd.sporjectone.single;

/**
 * Created by Administrator on 2017/2/15.
 */

public class Factory {
    public static Api create(){
        return new Impl();
    }
}
