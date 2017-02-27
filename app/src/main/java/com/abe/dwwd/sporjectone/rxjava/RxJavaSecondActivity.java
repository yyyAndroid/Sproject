package com.abe.dwwd.sporjectone.rxjava;

import android.app.Application;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abe.dwwd.sporjectone.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxJavaSecondActivity extends AppCompatActivity {

    TextView tvDesc;
    ImageView imageView;

    private static final String TAG = "RxJavaSecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_second);
        tvDesc = (TextView) findViewById(R.id.tv);
        imageView = (ImageView) findViewById(R.id.imageview);
    }

    /**
     * RxJava例子
     */
    public void clickStart(View view) {
        String[] names = new String[]{"one", "two", "three"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        tvDesc.setText(s);
                        Log.d(TAG, s);
                    }
                });
    }

    int drawableRes = R.mipmap.ic_launcher;

    public void clickStartTwo(View view) {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = RxJavaSecondActivity.this.getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(RxJavaSecondActivity.this, "Error " + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
        });
    }
    /*
        在RxJava中，Scheduler--调度器，相当于线程控制器，RxJava通过它来指定一段代码应该运行在什么样的线程。RxJava已经内置了几个Scheduler
            Schedulers.immediate():直接在当前线程运行，相当于不指定线程。这是默认的Scheduler。
            Schedulers.newThread():总是启用新线程
            Schedulers.io():I/O操作（读写文件，读写数据库，网络信息交互等）所使用的Scheduler。行为模式和newThread()差不多，quiet在于
            io()的背部实现是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下io()比newThread()更有效。不要把计算工作放在io()中，
            可以避免创建不必要的线程。
            Schedulers.computation():计算所使用的Scheduler。这是基三指的是CPU密集型计算，既不会被I/O等操作限制性能的操作，例如图形的计算
            ，这个Scheduler使用的固定的线程池,大小为CPU核数.不要把I/O造作操作放在computation()中，否则I/O造作的等待时间回廊坊CPU
            另外，Android还有一个专用的AndroidSchedulers.mainThread(),它指定操作将在Android主线程运行
     */

    public void clickSchedulers(View view) {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "number:" + integer);
                    }
                });
    }

    public void clickSchedulersTwo(View view) {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = RxJavaSecondActivity.this.getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        imageView.setImageDrawable(drawable);
                    }
                });
    }
}
