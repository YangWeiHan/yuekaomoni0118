package com.example.mryang.yuekaomoni0118.ui.actifity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.data.adapter.ShangjiaAdapter;
import com.example.mryang.yuekaomoni0118.data.bean.ShoppingBean;
import com.example.mryang.yuekaomoni0118.di.contrain.IContainShop;
import com.example.mryang.yuekaomoni0118.di.presenter.IShopPresenterlmpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
* 购物车逻辑
* */
public class ShopCarActivity extends AppCompatActivity implements IContainShop.IShopView,View.OnClickListener {

    @BindView(R.id.shop_recyclerView)
    RecyclerView shopRecyclerView;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.all_price)
    TextView allPrice;
    private IShopPresenterlmpl presenterlmpl;
    private Context context;
    private List<ShoppingBean.DataBean> busioness_data;
    private ShangjiaAdapter shangjiaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        ButterKnife.bind(this);
        context = ShopCarActivity.this;
        presenterlmpl = new IShopPresenterlmpl();
        presenterlmpl.attahView(this);
        presenterlmpl.goTORequestData();
    }
    //计算总价
    private void calculTotalCount(){
        double totalCount = 0;
        for (int i = 0; i < busioness_data.size(); i++) {
            for (int j = 0; j < busioness_data.get(i).getList().size(); j++) {
                if (busioness_data.get(i).getList().get(j).getGoodsChecked()){
                    double price = busioness_data.get(i).getList().get(j).getPrice();
                    int num = busioness_data.get(i).getList().get(j).getNum();
                    totalCount = totalCount + (price * num);
                }
            }
        }
        allPrice.setText("合计：￥"+String.valueOf(totalCount));
    }


    @Override
    public void setShopData(ShoppingBean shoppingBean) {
        cbAll.setOnCheckedChangeListener(null);
        cbAll.setOnClickListener(this);
        busioness_data = shoppingBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        shopRecyclerView.setLayoutManager(linearLayoutManager);
        shangjiaAdapter = new ShangjiaAdapter(R.layout.shangjia_item, busioness_data);
        shopRecyclerView.setAdapter(shangjiaAdapter);
        //所有子条目选中以后  商家条目也选中
        /************************************************************************************************/
        //得到商家适配器里回传回来的数据
        shangjiaAdapter.setOnBusinessItemClickLister(new ShangjiaAdapter.OnBusinessItemClickLister() {
            //给一个初变量 默认为fales   遍历商家  商品的所有条目
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i < busioness_data.size(); i++) {
                    //外层选中状态
                    boolean businessChecked = busioness_data.get(i).getBusinessChecked();
                    result = result & businessChecked;
                    for (int j = 0; j <busioness_data.get(i).getList().size() ; j++) {
                        //里层选中状态
                        boolean goodsChecked = busioness_data.get(i).getList().get(j).getGoodsChecked();
                        result = result & goodsChecked;
                    }
                }
                cbAll.setChecked(result);
                calculTotalCount();
            }
        });
        /************************************************************************************************/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterlmpl.detachView(this);
    }

    @Override
    public void onClick(View v) {
        //全选逻辑   先遍历商家  在遍历商品
        for (int i = 0; i < busioness_data.size(); i++) {
            busioness_data.get(i).setBusinessChecked(cbAll.isChecked());
            for (int j = 0; j < busioness_data.get(i).getList().size(); j++) {
                busioness_data.get(i).getList().get(j).setGoodsChecked(cbAll.isChecked());
            }
        }
        shangjiaAdapter.notifyDataSetChanged();
        calculTotalCount();

    }
}
