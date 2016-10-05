package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by zhangjingtao on 2016/9/30.
 */
public class ListActivity extends Activity {

    ListView lvList;
    EditText etConten;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvList = (ListView) findViewById(R.id.lv_list);
        etConten = (EditText) findViewById(R.id.et_content);
        btnSearch = (Button) findViewById(R.id.btn_search);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ListActivity.this,DetailActivity.class);
                intent.putExtra("content","");
                startActivity(intent);
            }
        });
        String string=getIntent().getExtras().getString("content");
        etConten.setText(string);
        lvList.setAdapter(new ArrayAdapter<String>(this,R.layout.item_list,HomeActivity.bkTree.sorted_query(string,1)));
    }

}
