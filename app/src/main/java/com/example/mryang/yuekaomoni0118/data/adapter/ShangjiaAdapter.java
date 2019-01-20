package com.example.mryang.yuekaomoni0118.data.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.data.bean.ShoppingBean;

import java.util.List;

public class ShangjiaAdapter extends BaseQuickAdapter<ShoppingBean.DataBean,BaseViewHolder> {
    OnBusinessItemClickLister onBusinessItemClickLister;

    public void setOnBusinessItemClickLister(OnBusinessItemClickLister onBusinessItemClickLister) {
        this.onBusinessItemClickLister = onBusinessItemClickLister;
    }

    public interface OnBusinessItemClickLister{
        public void onCallBack();
    }

    public ShangjiaAdapter(int layoutResId, @Nullable List<ShoppingBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingBean.DataBean item) {
        helper.setText(R.id.tv_business_name,item.getSellerName());
        RecyclerView rv_goods = helper.getView(R.id.rv_goods);
        final CheckBox cb_business = helper.getView(R.id.cb_business);

        List<ShoppingBean.DataBean.ListBean> goods_data = item.getList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        rv_goods.setLayoutManager(linearLayoutManager);
        final ShangpinAdapter shangpinAdapter = new ShangpinAdapter(R.layout.shangpin_item,goods_data);
        rv_goods.setAdapter(shangpinAdapter);
        cb_business.setOnCheckedChangeListener(null);
        cb_business.setChecked(item.getBusinessChecked());
        shangpinAdapter.setOnGoodsItemClickLisenter(new ShangpinAdapter.OnGoodsItemClickLisenter() {
            @Override
            public void onCallBack() {
                //遍历商家获取所有子条目，只要有一个未勾选，商品类别也应该是未勾选
                //首先设置一个变量    默认值为true
                boolean result = true;
                //遍历商家里的所有子条目
                for (int i = 0; i < item.getList().size(); i++) {
                    //这里的核心思想是  true&fales
                    result = result & item.getList().get(i).getGoodsChecked();
                }
                //得到一个状态值后  将其设置给 商家的状态值
                cb_business.setChecked(result);
                //刷新子条目
                shangpinAdapter.notifyDataSetChanged();

                onBusinessItemClickLister.onCallBack();
            }
        });

        //外层的商品类别条目需要控制里面的商品条目
        cb_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取商品的勾选状态
                for (int i = 0; i < item.getList().size(); i++) {
                    //获取到商家的选中状态
                    //之后给每一个子条目赋值
                    item.getList().get(i).setGoodsChecked(cb_business.isChecked());
                }

                item.setBusinessChecked(cb_business.isChecked());
                notifyDataSetChanged();
                //把最后的状态进行回传
                onBusinessItemClickLister.onCallBack();
            }
        });
    }
}
