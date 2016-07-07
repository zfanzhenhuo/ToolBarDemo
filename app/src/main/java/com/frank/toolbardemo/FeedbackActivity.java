package com.frank.toolbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class FeedbackActivity extends Activity implements View.OnClickListener {
    private Button positive;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_3);
        positive = (Button) findViewById(R.id.btn_pos);
        exit = (Button) findViewById(R.id.btn_cancel);
        positive.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pos:
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancel:
                finish();
        }

    }
}
