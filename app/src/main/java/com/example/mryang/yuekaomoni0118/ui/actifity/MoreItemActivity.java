package com.example.mryang.yuekaomoni0118.ui.actifity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.data.adapter.NewsAdapter;
import com.example.mryang.yuekaomoni0118.data.adapter.NewsTwoAdapter;
import com.example.mryang.yuekaomoni0118.data.bean.NewsBean;
import com.example.mryang.yuekaomoni0118.di.contrain.IContainNews;
import com.example.mryang.yuekaomoni0118.di.presenter.INewsPresenerlmpl;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreItemActivity extends AppCompatActivity implements IContainNews.INewsView {


    @BindView(R.id.news_recyclerView)
    XRecyclerView newsRecyclerView;
    private int page = 1;
    private INewsPresenerlmpl presenerlmpl;
    private Context context;
    private List<NewsBean.DataBean> beanData;
    private NewsTwoAdapter newsTwoAdapter;
    private List<NewsBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_item);
        ButterKnife.bind(this);
        context = MoreItemActivity.this;
        beanData = new ArrayList<>();
        presenerlmpl = new INewsPresenerlmpl();
        presenerlmpl.attahView(this);

        presenerlmpl.goToRequstNewsData(page);

        newsTwoAdapter = new NewsTwoAdapter(this);
        newsRecyclerView.setAdapter(newsTwoAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        newsRecyclerView.setLayoutManager(linearLayoutManager);

        newsRecyclerView.setLoadingMoreEnabled(true);
        newsRecyclerView.setPullRefreshEnabled(true);
        newsRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                beanData.clear();
                presenerlmpl.goToRequstNewsData(page);

            }

            @Override
            public void onLoadMore() {
                page++;
                presenerlmpl.goToRequstNewsData(page);
            }
        });
        newsTwoAdapter.setOnItemClickListener(new NewsTwoAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, final int position) {
                /*AlertDialog  的使用
                * */
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("不再关注");
                builder.setMessage("确定取消关注此类新闻吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);

                        newsTwoAdapter.notifyDataSetChanged();

                        Log.d("TAG",data.size()+"");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "您已取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public void getNewsData(NewsBean newsBean) {
        data = newsBean.getData();
        if (page == 1){
            newsTwoAdapter.setNewsList(data);
        }else if (page > 1){
            newsTwoAdapter.addNewsList(data);
        }

        newsRecyclerView.refreshComplete();
        newsRecyclerView.loadMoreComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenerlmpl.detachView(this);
    }

/*             */
}
