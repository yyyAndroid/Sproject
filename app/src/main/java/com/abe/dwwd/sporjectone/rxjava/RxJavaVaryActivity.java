package com.abe.dwwd.sporjectone.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.abe.dwwd.sporjectone.R;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * RxJava之变换
 */
public class RxJavaVaryActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaVaryActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_vary);
    }

    /*
    RxJava提供了对事件序列进行变换的支持，这是他的核心功能之一，也是大多数人说RxJava真是太好用了的最大原因。
    所谓变换：就是将事件中的对象或者整个序列进行加工处理，转换不同的事件或者事件序列。概念总是说着模糊难懂，来看API*/
    //首先看一个map()的例子
    public void clcckVaryMap(){
        Observable.just("images/logo.png")
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return getBitmapFromPath(s);
                    }
                }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                Log.d(TAG,"显示bitmap");
            }
        });
    }

    private Bitmap getBitmapFromPath(String s) {
//        return new BitmapFactory.decodeFile(s);
        return null;
    }
}
