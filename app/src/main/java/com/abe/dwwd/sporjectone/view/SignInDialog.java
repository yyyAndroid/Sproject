package com.abe.dwwd.sporjectone.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.abe.dwwd.sporjectone.R;


/**
 * Created by yanyangy on 2017/2/23.
 * 签到dialog
 */

public class SignInDialog extends Dialog {
    ImageView ivBack;//返回
    ImageView ivHeader;//头像
    String headerUrl;
    View view;
    public SignInDialog(Context context, String headerUrl) {
        super(context, R.style.sign_in_dialog);
        this.headerUrl = headerUrl;
        init();
    }

    private void init(){
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_sign_in, null);
        ivBack = (ImageView) view.findViewById(R.id.sign_in_back);
        ivHeader = (ImageView) view.findViewById(R.id.sign_in_header);

        setContentView(view);

        configurationWindow();
    }

    /**
     * 配置windows
     */
    private void configurationWindow() {
        this.setCanceledOnTouchOutside(false);
        Window select_window = getWindow();
        select_window.setGravity(Gravity.TOP);
        WindowManager.LayoutParams lp = select_window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        select_window.setAttributes(lp);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();

    }
}
