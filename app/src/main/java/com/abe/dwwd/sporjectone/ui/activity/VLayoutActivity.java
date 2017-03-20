package com.abe.dwwd.sporjectone.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.abe.dwwd.sporjectone.R;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;


/**
 * VirtualLayout是针对RecyclerView的LayoutManager扩展，主要提供一整套布局方案和布局间的组件复用问题
 */
public class VLayoutActivity extends BaseActivity {

//    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initViews();
    }

    private void initViews(){
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //设置回收池大小
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);

        //加载数据
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager,false);
        recyclerView.setAdapter(delegateAdapter);

    }

}
