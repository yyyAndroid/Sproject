package com.abe.dwwd.sporjectone.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.abe.dwwd.sporjectone.R;
import com.abe.dwwd.sporjectone.view.refresh.OnRefreshListener;
import com.abe.dwwd.sporjectone.view.refresh.RefreshLayout;

import butterknife.BindView;

public class PullToRefreshActivity extends BaseActivity {

    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;

    @BindView(R.id.text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_reflsh);
        refreshLayout = (RefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setEnabled(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(PullToRefreshActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();

                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });



    }



}
