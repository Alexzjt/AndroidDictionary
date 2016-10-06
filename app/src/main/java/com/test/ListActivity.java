package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dataStructure.SpellChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjingtao on 2016/9/30.
 */
public class ListActivity extends Activity{
    int radius;
    ListView lvList;
    EditText etConten;
    Button btnSearch;
    Button btnMore;
    ArrayList<String> list=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        radius=1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvList = (ListView) findViewById(R.id.lv_list);
        etConten = (EditText) findViewById(R.id.et_content);
        btnSearch = (Button) findViewById(R.id.btn_search);
        btnMore = (Button) findViewById(R.id.btn_more);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ListActivity.this,DetailActivity.class);
                String temp=lvList.getItemAtPosition(position).toString();
                intent.putExtra("content",temp);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etConten.getText())) {
                    Intent intent = new Intent();
                    intent.setClass(ListActivity.this,DetailActivity.class);
                    intent.putExtra("content",etConten.getText().toString());
                    startActivity(intent);
                }
            }
        });

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=getIntent().getExtras().getString("content");
                ArrayList<String> temp = HomeActivity.bkTree.sorted_query(string,radius++);
                SpellChecker.sortList(temp,HomeActivity.wordFrequencyMap,string);
                SpellChecker.addAllWithoutRepeat(list,temp,string);
                //list.clear();
                adapter.notifyDataSetChanged();
                lvList.setAdapter(adapter);
            }
        });

        String string=getIntent().getExtras().getString("content");
        etConten.setText(string);
//        list = HomeActivity.bkTree.sorted_query(string,radius);
        adapter = new ArrayAdapter<String>(this,R.layout.item_list,list);
        lvList.setAdapter(adapter);
        btnMore.performClick();

    }

//    @Override
//    protected void onResume() {
//        radius=1;
//        super.onResume();
//        String string=getIntent().getExtras().getString("content");
//        etConten.setText(string);
//        lvList.setAdapter(new ArrayAdapter<String>(this,R.layout.item_list,HomeActivity.bkTree.sorted_query(string,1)));
//    }
}
