package com.example.mryang.yuekaomoni0118.data.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.data.bean.GoodsBean;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<GoodsBean.DataBean.ListBean,BaseViewHolder> {
    public GoodsAdapter(int layoutResId, @Nullable List<GoodsBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.DataBean.ListBean item) {
        ImageView goods_image = helper.getView(R.id.goods_image);
        Glide.with(mContext).load(item.getIcon()).into(goods_image);

    }
}
