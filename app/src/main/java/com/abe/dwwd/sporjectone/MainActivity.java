package com.abe.dwwd.sporjectone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.abe.dwwd.sporjectone.ui.activity.BaseActivity;
import com.abe.dwwd.sporjectone.ui.activity.ChartActivity;
import com.abe.dwwd.sporjectone.ui.activity.MoveActivity;
import com.abe.dwwd.sporjectone.ui.activity.OkHttpActivity;
import com.abe.dwwd.sporjectone.ui.activity.PullToRefreshActivity;
import com.abe.dwwd.sporjectone.ui.activity.ViewPageActivity;
import com.abe.dwwd.sporjectone.view.SignInDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    Button btChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btChart = (Button) findViewById(R.id.bt_chart);
    }

    public void clickChart(View view){
        if (btChart == null){
            Toast.makeText(this, "bt为空", Toast.LENGTH_SHORT).show();
        }
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

    public void clickSignDialog(View view){
        SignInDialog signInDialog = new SignInDialog(this,null);
        signInDialog.show();
    }
}
