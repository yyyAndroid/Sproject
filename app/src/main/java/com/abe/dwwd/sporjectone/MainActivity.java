package com.abe.dwwd.sporjectone;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.abe.dwwd.sporjectone.net.HttpUtils;
import com.abe.dwwd.sporjectone.view.CustomCheckView;
import com.abe.dwwd.sporjectone.view.CustomHorzinonlChartView;

public class MainActivity extends AppCompatActivity {
    CustomCheckView checkView;
    CustomHorzinonlChartView chartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chartView = (CustomHorzinonlChartView) findViewById(R.id.charview);
        checkView = (CustomCheckView) findViewById(R.id.checkview);
        checkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkView.isCheck){
                    checkView.unCheck();
                }else{
                    checkView.check();
                }
            }
        });

        chartView.setNumber(50);

        checkView.postDelayed(new Runnable() {
            @Override
            public void run() {
                new HttpUtils().getRequest();
            }
        },1000);
    }
}
