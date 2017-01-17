package com.abe.dwwd.sporjectone;

import android.Manifest;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abe.dwwd.sporjectone.net.HttpUtils;
import com.abe.dwwd.sporjectone.ui.BaseActivity;
import com.abe.dwwd.sporjectone.utils.LogUtils;
import com.abe.dwwd.sporjectone.view.CustomCheckView;
import com.abe.dwwd.sporjectone.view.CustomHorzinonlChartView;
import com.orhanobut.logger.Logger;

public class MainActivity extends BaseActivity {
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
                //检查权限
                if (checkAppPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,0,"申请SD卡权限")){
                    Toast.makeText(MainActivity.this, "已获得读写SD卡权限", Toast.LENGTH_SHORT).show();
                }
                if (checkAppPermission(Manifest.permission.WRITE_CONTACTS,1,"申请读写通讯录权限")){
                    Toast.makeText(MainActivity.this, "已获得通讯录权限", Toast.LENGTH_SHORT).show();
                }
            }
        },1000);

    }

    @Override
    protected void permissionCallback(boolean successed, int requestCode, String permission) {
        super.permissionCallback(successed, requestCode, permission);
        if (successed){
            Toast.makeText(this, permission+"权限申请成功!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, permission+"权限申请失败", Toast.LENGTH_SHORT).show();
        }
    }
}
