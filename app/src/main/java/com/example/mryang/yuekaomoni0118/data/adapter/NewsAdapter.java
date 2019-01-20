package com.example.mryang.yuekaomoni0118.data.adapter;

import android.media.Image;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.data.bean.NewsBean;

import java.util.List;

public class NewsAdapter extends BaseQuickAdapter<NewsBean.DataBean,BaseViewHolder> {

    public NewsAdapter(int layoutResId, @Nullable List<NewsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean item) {
        helper.setText(R.id.tv_name,item.getAuthor_name());
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        Glide.with(mContext).load(item.getThumbnail_pic_s()).into(iv_icon);
    }
}
