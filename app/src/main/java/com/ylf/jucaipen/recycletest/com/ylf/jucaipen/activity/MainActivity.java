package com.ylf.jucaipen.recycletest.com.ylf.jucaipen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.ylf.jucaipen.recycletest.R;
import com.ylf.jucaipen.recycletest.com.ylf.jucaipen.view.MyListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements MyListView.IRefreshListener {

    private MyListView myListView;
    private SimpleAdapter simple_adapter;
    private List<Map<String, Object>> list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            myListView = (MyListView) findViewById(R.id.myListView);
            iniData();   //初始化数据，我们给它加20条Item
            simple_adapter = new SimpleAdapter(MainActivity.this, list,
                    R.layout.ui_item, new String[] { "image", "text" },
                    new int[] { R.id.image, R.id.text });
            //设置适配器
            myListView.setAdapter(simple_adapter);
            //设置更新数据的接口
            myListView.setInterface(this);
            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent test=new Intent();
                    test.setClass(MainActivity.this,TestActivity.class);
                    MainActivity.this.startActivity(test);
                }
            });
        }

        // 初始化SimpleAdapter数据集
        private List<Map<String, Object>> iniData() {
            list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < 20; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                //解释下这里的数据，key对应SimpleAdapter的第三个参数，必须都包含它们。值对应第五个参数，分别是图片和文字
                map.put("text", i);
                map.put("image", R.mipmap.ic_launcher);
                list.add(map);
            }

            return list;
        }
        /**
         * 接口回调，在RefreshListView中可以调用此方法进行数据添加。
         */
        @Override
        public void onRefresh() {
            // TODO 自动生成的方法存根
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("text", "滚动添加 ");
                    map.put("image", R.mipmap.ic_launcher);
                    list.add(0, map);
                    myListView.setAdapter(simple_adapter);
                    simple_adapter.notifyDataSetChanged();
                    myListView.refreshComplete();
                }
            }, 2000);
        }

    }
