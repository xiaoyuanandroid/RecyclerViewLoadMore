package com.xiaoyuan;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xiaoyuan.interfaces.OnLoadMoreListener;
import com.xiaoyuan.interfaces.OnMultiTypeItemClickListeners;
import com.xiaoyuan.viewholder.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xiaoyuan
 */
public class MainActivity extends AppCompatActivity {

    private LoadMoreAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private boolean isFailed = true;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE);
        //初始化adapter
        mAdapter = new LoadMoreAdapter(this, null, true);
        mAdapter.setAddHaed(true);//设置有头部
        //点击事件
        mAdapter.setOnMultiTypeItemClickListener(new OnMultiTypeItemClickListeners<String>() {
            @Override
            public void onItemClick(CommonViewHolder viewHolder, String data, int position, int viewType) {
                if(data == null){
                    Toast.makeText(MainActivity.this, "我是头部", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                }

            }
        });

        //刷新数据监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();

            }
        });

        //加载更多事件
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        loadData();//初始化数据

    }

    /**
     * 初始化数据
     */
    private void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> data = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    data.add(i+"");
                }
                //刷新数据
                mAdapter.setInitData(data);
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setLoadingView(R.layout.load_loading);
            }
        }, 2000);
    }

    private void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAdapter.getItemCount() > 30 && isFailed) {
                    isFailed = false;
                    //加载失败
                    mAdapter.setLoadFailedView(R.layout.load_failed);
                } else if (mAdapter.getItemCount() > 50) {
                    //加载完成
                    mAdapter.setLoadEndView(R.layout.load_end);
                } else {

                    final List<String> data = new ArrayList<>();
                    for (int i = 0; i < 12; i++) {
                        data.add("我是加载更多" + i);
                    }
                    mAdapter.setLoadMoreData(data);
                }
            }
        }, 3000);
    }
}
