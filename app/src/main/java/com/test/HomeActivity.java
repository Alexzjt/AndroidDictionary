package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dataStructure.BKTree;
import com.dataStructure.SpellChecker;

import java.util.HashMap;

/**
 * Created by zhangjingtao on 2016/9/30.
 */
public class HomeActivity extends Activity{

    EditText etConten;
    Button btnSearch;
    public static BKTree bkTree = null;
    public static HashMap<String,Integer> wordFrequencyMap=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etConten = (EditText) findViewById(R.id.et_content);
        btnSearch = (Button) findViewById(R.id.btn_search);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(HomeActivity.bkTree==null){}
                if (!TextUtils.isEmpty(etConten.getText())) {
                    Intent intent = new Intent();
                    intent.setClass(HomeActivity.this,DetailActivity.class);
                    intent.putExtra("content",etConten.getText().toString());
                    startActivity(intent);
                }
            }
        });
        MyTask myTask=new MyTask();
        myTask.execute(this);
    }
    private class MyTask extends AsyncTask<Activity, Integer,Boolean> {
        @Override
        protected Boolean doInBackground(Activity... params) {
            try{
                bkTree=SpellChecker.getBKTree(params[0]);
                wordFrequencyMap=SpellChecker.getWordFrequency(params[0]);
                return true;
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            return false;
        }
    }
}
