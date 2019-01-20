package com.example.mryang.yuekaomoni0118.ui.actifity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.data.adapter.BusionessAdapter;
import com.example.mryang.yuekaomoni0118.data.adapter.DetilesAdapter;
import com.example.mryang.yuekaomoni0118.data.bean.BusionessBean;
import com.example.mryang.yuekaomoni0118.data.bean.GoodsBean;
import com.example.mryang.yuekaomoni0118.di.contrain.IContrainBusioness;
import com.example.mryang.yuekaomoni0118.di.presenter.IBusionessPresenterlmpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
* 二级联动
*
* */
public class LinkageEffectActivity extends AppCompatActivity implements IContrainBusioness.IBusionessView {

    @BindView(R.id.left_recyclerView)
    RecyclerView leftRecyclerView;
    @BindView(R.id.right_recyclerView)
    RecyclerView rightRecyclerView;
    private IBusionessPresenterlmpl presenterlmpl;
    private Context context;
    private BusionessAdapter busionessAdapter;
    private List<BusionessBean.DataBean> busionessBeanData;
    private List<GoodsBean.DataBean> goodsBeanData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = LinkageEffectActivity.this;
        setContentView(R.layout.activity_linkage_effect);
        ButterKnife.bind(this);
        presenterlmpl = new IBusionessPresenterlmpl();
        presenterlmpl.attchView(this);
        presenterlmpl.goTORequestData();

    }

    @Override
    public void setBusiessData(final BusionessBean busionessBean) {
        busionessBeanData = busionessBean.getData();
        for (int i = 0; i < busionessBeanData.size(); i++) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            leftRecyclerView.setLayoutManager(linearLayoutManager);
            busionessAdapter = new BusionessAdapter(R.layout.left_goods_item,busionessBeanData);
            leftRecyclerView.setAdapter(busionessAdapter);
        }
        busionessAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int cid = busionessBeanData.get(position).getCid();
                presenterlmpl.goTORequestDetilesData(cid);
            }
        });
    }

    @Override
    public void setGoodsData(GoodsBean goodsBean) {
        goodsBeanData = goodsBean.getData();
       LinearLayoutManager linearLayoutManage = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
       rightRecyclerView.setLayoutManager(linearLayoutManage);
        DetilesAdapter detilesAdapter = new DetilesAdapter(R.layout.detile_goods_item, goodsBeanData);
        rightRecyclerView.setAdapter(detilesAdapter);

    }
}
