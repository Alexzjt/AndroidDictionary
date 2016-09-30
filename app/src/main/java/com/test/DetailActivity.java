package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dataStructure.BKTree;
import com.dataStructure.SpellChecker;
import com.translate.TranslateUtil;

import java.util.List;

/**
 * Created by zhangjingtao on 2016/9/30.
 */
public class DetailActivity extends Activity {

    TextView tvDetail;
    Button btnFirst;
    Button btnMore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetail = (TextView) findViewById(R.id.tv_detail);
        btnFirst = (Button) findViewById(R.id.btn_first);
        btnMore = (Button) findViewById(R.id.btn_more);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DetailActivity.this,ListActivity.class);
                intent.putExtra("content","");
                startActivity(intent);
            }
        });
        //BKTree<String> bkTree = SpellChecker.getBKTree();
        String string=getIntent().getExtras().getString("content");
        String result=TranslateUtil.tarnslate(string);
        tvDetail.setText(result);
    }
}
