package com.abe.dwwd.sporjectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.abe.dwwd.sporjectone.ui.activity.BaseActivity;
import com.abe.dwwd.sporjectone.ui.activity.ChartActivity;
import com.abe.dwwd.sporjectone.ui.activity.MoveActivity;
import com.abe.dwwd.sporjectone.ui.activity.OkHttpActivity;
import com.abe.dwwd.sporjectone.ui.activity.PullToRefreshActivity;
import com.abe.dwwd.sporjectone.ui.activity.ViewPageActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickChart(View view){
        startActivity(new Intent(this, ChartActivity.class));
    }

    public void clickOkHttp(View view){
        startActivity(new Intent(this, OkHttpActivity.class));
    }

    public void clickViewpage(View view){
        startActivity(new Intent(this, ViewPageActivity.class));
    }

    public void clickMove(View view){
        startActivity(new Intent(this, MoveActivity.class));
    }

    public void clickRefresh(View view){
        startActivity(new Intent(this, PullToRefreshActivity.class));
    }
}
