package com.abe.dwwd.sporjectone.net;

import com.abe.dwwd.sporjectone.utils.LogUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/16.
 */

public class HttpUtils{
    //创建okHttpClient对象
    OkHttpClient mOkHttpClient= new OkHttpClient();
    //创建一个Request
    final Request request= new Request.Builder()
            .url("http://www.weather.com.cn/data/cityinfo/101010100.html")
            .build();
    //new call
     Call call = mOkHttpClient.newCall(request);
    //请求加入调度
    private static final String TAG = "HttpUtils";
    public void getRequest(){
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                LogUtils.json(response.body().string());
            }
        });
    }
}
