package com.abe.dwwd.sporjectone.ui;

import android.os.Bundle;
import android.view.View;

import com.abe.dwwd.sporjectone.R;
import com.abe.dwwd.sporjectone.view.CustomHorzinonlChartView;

public class ChartActivity extends BaseActivity {
    CustomHorzinonlChartView customHorzinonlChartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        customHorzinonlChartView = (CustomHorzinonlChartView) findViewById(R.id.customHorzinonlChartView);
        customHorzinonlChartView.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        },2000);

    }

    public void clickBt(View view){
        customHorzinonlChartView.setNumber(90);
        customHorzinonlChartView.startAnimation();
    }
}
