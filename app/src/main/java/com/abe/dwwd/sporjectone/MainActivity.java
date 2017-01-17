package com.abe.dwwd.sporjectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.abe.dwwd.sporjectone.ui.BaseActivity;
import com.abe.dwwd.sporjectone.ui.ChartActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickChart(View view){
        startActivity(new Intent(this, ChartActivity.class));
    }
}
