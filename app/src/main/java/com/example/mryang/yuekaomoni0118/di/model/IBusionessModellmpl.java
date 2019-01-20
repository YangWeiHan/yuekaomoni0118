package com.example.mryang.yuekaomoni0118.di.model;

import com.example.mryang.yuekaomoni0118.data.apils.Aplis;
import com.example.mryang.yuekaomoni0118.data.bean.BusionessBean;
import com.example.mryang.yuekaomoni0118.data.bean.GoodsBean;
import com.example.mryang.yuekaomoni0118.di.contrain.IContrainBusioness;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;


public class IBusionessModellmpl implements IContrainBusioness.IBusionessModel {


    @Override
    public void contrainModel(final OnCallback onCallback) {
        OkGo.<String>get(Aplis.BUSIONESS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Gson gson = new Gson();
                BusionessBean busionessBean = gson.fromJson(responseData, BusionessBean.class);
                onCallback.callback(busionessBean);

            }
        });
    }
    /*
    * 注意 = 号
    * */
    @Override
    public void contrainGoodsModel(int cid,final OnGoodsCallBack onGoodsCallBack) {
        OkGo.<String>get(Aplis.GOODS_URL + "?cid=" + cid).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Gson gson = new Gson();
                GoodsBean goodsBean = gson.fromJson(responseData, GoodsBean.class);
                onGoodsCallBack.onCallback(goodsBean);
            }
        });
    }
}
