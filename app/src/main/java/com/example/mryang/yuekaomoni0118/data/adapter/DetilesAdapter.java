package com.example.mryang.yuekaomoni0118.data.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.data.bean.GoodsBean;

import java.util.List;

public class DetilesAdapter extends BaseQuickAdapter<GoodsBean.DataBean,BaseViewHolder> {
    public DetilesAdapter(int layoutResId, @Nullable List<GoodsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.DataBean item) {
        helper.setText(R.id.detile_busioness_name,item.getName());
        RecyclerView detiles_recyclerView = helper.getView(R.id.detiles_recyclerView);

        List<GoodsBean.DataBean.ListBean> goodsList = item.getList();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false);
        detiles_recyclerView.setLayoutManager(gridLayoutManager);

        GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.right_goods_item,goodsList);
        detiles_recyclerView.setAdapter(goodsAdapter);
    }
}
