package com.ylf.jucaipen.recycletest.com.ylf.jucaipen.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ylf.jucaipen.recycletest.R;
import com.ylf.jucaipen.recycletest.com.ylf.jucaipen.com.ylf.jucaipen.adapter.RecycleAdapter;

/**
 * Created by Administrator on 2015/11/9.
 */
public class ListsActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    @ViewInject(R.id.recycle_lv)
    private RecyclerView recycle_lv;
    @ViewInject(R.id.swipe_refresh_widget)
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecycleAdapter adapter;
    private int lastVisibleItem;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_list);
        ViewUtils.inject(this);
       initView();

    }

    private void initView() {
        recycle_lv.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recycle_lv.setLayoutManager(mLayoutManager);
        recycle_lv.setItemAnimator(new DefaultItemAnimator());
        adapter=new RecycleAdapter();
        recycle_lv.setAdapter(adapter);
        //设置 进度条的颜色变化，最多可以设置4种颜色
        mSwipeRefreshWidget.setColorSchemeResources(android.R.color.holo_red_light,android.R.color.holo_orange_light,android.R.color.holo_green_light);
        mSwipeRefreshWidget.setOnRefreshListener(this);
        //第一次进入页面的时候显示加载进度条  调整进度条距离屏幕顶部的距离
        mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        recycle_lv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState!=RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==adapter.getItemCount()){
                     mSwipeRefreshWidget.setRefreshing(true);
                    //请求数据
                   // handler.sendEmptyMessageDelayed(0, 3000);
                }else if(newState==RecyclerView.SCROLL_STATE_DRAGGING){

                }
            }
        });
    }

    @Override
    public void onRefresh() {
        //刷新操作

    }
}
