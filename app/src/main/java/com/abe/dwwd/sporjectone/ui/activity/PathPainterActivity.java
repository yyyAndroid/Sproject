package com.abe.dwwd.sporjectone.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abe.dwwd.sporjectone.R;


public class PathPainterActivity extends BaseActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_painter);
        editText = (EditText) findViewById(R.id.edit_query);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

                    String keytag = editText.getText().toString().trim();

                    if (keytag.isEmpty()) {
                        Toast.makeText(PathPainterActivity.this, "请输入搜索关键字", Toast.LENGTH_SHORT).show();

                        return false;
                    }
                    // 搜索功能主体
                    Toast.makeText(PathPainterActivity.this, "搜索"+keytag, Toast.LENGTH_SHORT).show();
                    return false;
                }
                return false;
            }
        });


    }


}
