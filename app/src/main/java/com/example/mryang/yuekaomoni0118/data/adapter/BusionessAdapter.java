package com.example.mryang.yuekaomoni0118.data.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.data.bean.BusionessBean;

import java.util.List;

public class BusionessAdapter extends BaseQuickAdapter<BusionessBean.DataBean,BaseViewHolder> {


    public BusionessAdapter(int layoutResId, @Nullable List<BusionessBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BusionessBean.DataBean item) {
        helper.setText(R.id.busioness_name,item.getName());
    }
}
