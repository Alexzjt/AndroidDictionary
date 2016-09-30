package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by zhangjingtao on 2016/9/30.
 */
public class ListActivity extends Activity {

    ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvList = (ListView) findViewById(R.id.lv_list);
        lvList.setAdapter(new ArrayAdapter<String>(this,R.layout.item_list,new String[]{"单词1","单词2","单词3","单词4","单词5"}));

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ListActivity.this,DetailActivity.class);
                intent.putExtra("content","");
                startActivity(intent);
            }
        });

    }

}
