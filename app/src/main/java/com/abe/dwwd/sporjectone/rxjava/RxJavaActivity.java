package com.abe.dwwd.sporjectone.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.abe.dwwd.sporjectone.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.ActionN;


public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
    }


    /**
     * 创建 Observer观察者
     */
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.d(TAG, TAG + "  onCompleted()");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, TAG + "   onError()" + e);
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, TAG + "   s:" + s);
        }
    };
    /**
     * RxJava还内置了一个实现Observer的抽象类：Subscriber.Subscriber对Observer接口进行了一些扩展，但他们的基本使用方式是完全一样的：
     */

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.d(TAG, TAG + " onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, TAG + "  onError():" + e);
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, TAG + "   onNext():" + s);
        }
    };


    /**
     * 这里传入了一个OnSubscribe对象作为参数。OnSubscribe会被存储在返回的Observable对象中，它的作用相当于一个计划表，当
     * Observable被订阅的时候，OnSubscriber的call()方法 会自动被调用，时间序列就会依照设定一次触发（对于上面的代码，就是观察者Subscriber将会
     * 被调用三次onNext()和一次 onComplete()。这样，有被观察者调用了观察者的回调方法，就实现了由被观察者向观察者的事件传递，既观察者模式。
     */
    Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("abe");
            subscriber.onCompleted();
        }
    });

    /**
     * create()方法是RxJava最基本的创造事件序列的方法。基于这个方法，RxJava还提供了一些方法用来快捷创建事件队列，例如：
     * <p>
     * just(T...):将传入的参数一次发送出来。
     */

    Observable observable2 = Observable.just("Hello", "hi", "abe");
    /*将会一次调用：
    onNext("Hello");
    onNext("Hi");
    noNext("abe");
    onCompleted();
    */

    /**
     * from(T[]) / from(Iterable<? extend T>); 将传入的数组或Iterable拆分成具体对象后，依次发送出来。
     */

    String[] words = {"Hello", "Hi", "abe"};
    Observable observable3 = Observable.from(words);
    /*
    讲一次调用：
    onNext("Hello");
    onNext("Hi");
    onNext("abe");
    onCompleted();
     */
    //上面just（T...)的例子，都和之前的create（OnSubscribe)的例子是等价的



    /**
     * 除了subscribe(Observer)和subscriber(Subscriber),subscribe()还支持不完整定义的回调，
     * RxJava会自动根据定义创建出Subscriber。形式如下：
     */

    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Log.d(TAG," onNext "+s);
        }
    };

    Action1<String> onErrorAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Log.d(TAG,"error :"+s);
        }
    };

    Action0 onCompleteAction = new Action0() {
        @Override
        public void call() {
            Log.d(TAG," onCompleteAction ");
        }
    };
    /**
     * Subscribe(订阅)
     */
    public void init() {
        //observable.subscribe(subscriber);
        //或者
        //observable.subscribe(observer);
        //自动创建 Subscriber，并使用onNextAction 来定义 onNext();
        observable.subscribe(onNextAction);
        //自动创建 Subscriber, 并使用onNextAction和 onErrorAction()来定义 onNext()和onError();
        observable.subscribe(onNextAction,onErrorAction);

        //自动创建 Subscriber,并使用onNextAction 和 onErrorAction() 和 onCompleteAction() 来定义 onNext()和onError和 onComplete();
        observable.subscribe(onNextAction,onErrorAction,onCompleteAction);

    }

//简单的RxJava例子
    public void clickBt(View view){
        String[] names = new String[]{"",""};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG,s);
            }
        });
    }



}
