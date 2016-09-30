package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by zhangjingtao on 2016/9/30.
 */
public class HomeActivity extends Activity{

    EditText etConten;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etConten = (EditText) findViewById(R.id.et_content);
        btnSearch = (Button) findViewById(R.id.btn_search);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etConten.getText())) {
                    Intent intent = new Intent();
                    intent.setClass(HomeActivity.this,DetailActivity.class);
                    intent.putExtra("content",etConten.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

}
