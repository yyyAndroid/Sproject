package com.abe.dwwd.sporjectone.ui;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.abe.dwwd.sporjectone.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/16.
 */

public class BaseActivity extends AppCompatActivity {
    /**
     * group:android.permission-group.CONTACTS//联系人
     * permission:android.permission.WRITE_CONTACTS//写写联系人
     * permission:android.permission.GET_ACCOUNTS//得到账号
     * permission:android.permission.READ_CONTACTS//读联系人
     * <p>
     * group:android.permission-group.PHONE
     * permission:android.permission.READ_CALL_LOG
     * permission:android.permission.READ_PHONE_STATE
     * permission:android.permission.CALL_PHONE
     * permission:android.permission.WRITE_CALL_LOG
     * permission:android.permission.USE_SIP
     * permission:android.permission.PROCESS_OUTGOING_CALLS
     * permission:com.android.voicemail.permission.ADD_VOICEMAIL
     * <p>
     * group:android.permission-group.CALENDAR
     * permission:android.permission.READ_CALENDAR
     * permission:android.permission.WRITE_CALENDAR
     * <p>
     * group:android.permission-group.CAMERA
     * permission:android.permission.CAMERA
     * <p>
     * group:android.permission-group.SENSORS
     * permission:android.permission.BODY_SENSORS
     * <p>
     * group:android.permission-group.LOCATION
     * permission:android.permission.ACCESS_FINE_LOCATION
     * permission:android.permission.ACCESS_COARSE_LOCATION
     * <p>
     * group:android.permission-group.STORAGE
     * permission:android.permission.READ_EXTERNAL_STORAGE
     * permission:android.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * group:android.permission-group.MICROPHONE
     * permission:android.permission.RECORD_AUDIO
     * <p>
     * group:android.permission-group.SMS
     * permission:android.permission.READ_SMS
     * permission:android.permission.RECEIVE_WAP_PUSH
     * permission:android.permission.RECEIVE_MMS
     * permission:android.permission.RECEIVE_SMS
     * permission:android.permission.SEND_SMS
     * permission:android.permission.READ_CELL_BROADCASTS
     *
     * @param savedInstanceState
     * @param persistentState
     */
    private static final String TAG = "BaseActivity";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 检查权限并申请
     */
    private Map<Integer, String> requestCodes;

    protected boolean checkAppPermission(final String permission, final int requestCode, String message) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                new AlertDialog.Builder(this)
                        .setMessage(message)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermission(permission, requestCode);
                            }
                        }).show();
            } else {
                //申请权限
                requestPermission(permission, requestCode);
            }
            return false;
        }
        return true;
    }

    /**
     * 申请权限
     */
    private void requestPermission(String permission, int requestCode) {

        if (requestCodes == null) {
            requestCodes = new HashMap();
        }
        if (!requestCodes.containsKey(requestCode)) {
            requestCodes.put(requestCode, permission);
        }
        //申请权限
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCodes.containsKey(requestCode)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionCallback(true, requestCode, requestCodes.get(requestCode));
            } else {
                permissionCallback(false, requestCode, requestCodes.get(requestCode));
            }
            requestCodes.remove(requestCode);
        }
    }

    /**
     * 权限返回状态
     *
     * @param successed
     * @param requestCode
     */
    protected void permissionCallback(boolean successed, int requestCode, String permission) {
        LogUtils.d("permission:" + permission + " requestCode:" + requestCode + " success:" + successed);
    }
}
