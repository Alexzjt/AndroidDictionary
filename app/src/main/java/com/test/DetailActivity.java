package com.test;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dataStructure.BKTree;
import com.dataStructure.SpellChecker;
import com.translate.TranslateUtil;

import java.util.List;
import java.io.*;

/**
 * Created by zhangjingtao on 2016/9/30.
 */
public class DetailActivity extends Activity {

    TextView tvDetail;
    Button btnFirst;
    Button btnMore;
    EditText etConten;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetail = (TextView) findViewById(R.id.tv_detail);
        btnFirst = (Button) findViewById(R.id.btn_first);
        btnMore = (Button) findViewById(R.id.btn_more);
        etConten = (EditText) findViewById(R.id.et_content);
        btnSearch = (Button) findViewById(R.id.btn_search);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(HomeActivity.wordFrequencyMap==null){}
                if (!TextUtils.isEmpty(etConten.getText())) {
                    Intent intent = new Intent();
                    intent.setClass(DetailActivity.this,ListActivity.class);
                    intent.putExtra("content",etConten.getText().toString());
                    startActivity(intent);
                }
            }
        });

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etConten.setText(btnFirst.getText());
                btnSearch.performClick();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyTask myTask=new MyTask();
                myTask.execute(etConten.getText().toString().trim());
                btnFirst.setText("正在获取候选词");
                while(HomeActivity.bkTree==null){}
                btnFirst.setText(HomeActivity.bkTree.getMostSimilar(etConten.getText().toString()));
            }
        });

        String string=getIntent().getExtras().getString("content");
        etConten.setText(string);
        btnSearch.performClick();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String string=getIntent().getExtras().getString("content");
        etConten.setText(string);
        btnSearch.performClick();
    }

    private class MyTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            tvDetail.setText("正在获取释义…………");
        }

        @Override
        protected String doInBackground(String... params) {
            if(HomeActivity.bkTree.contains(params[0])||SpellChecker.checkWord(params[0])) {
                String result = TranslateUtil.tarnslate(params[0]);
                if (!params[0].equals(result)) {
                    return result;
                }
            }
            return "您的输入有误，请查看下方候选单词";
        }

        @Override
        protected void onPostExecute(String s) {
            tvDetail.setText(s);
        }
    }
}
