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
import rx.functions.Action1;

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


}
