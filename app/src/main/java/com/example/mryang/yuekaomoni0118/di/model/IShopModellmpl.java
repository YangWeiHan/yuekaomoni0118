package com.example.mryang.yuekaomoni0118.di.model;

import com.example.mryang.yuekaomoni0118.data.apils.Aplis;
import com.example.mryang.yuekaomoni0118.data.bean.ShoppingBean;
import com.example.mryang.yuekaomoni0118.di.contrain.IContainShop;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IShopModellmpl implements IContainShop.IShopModel {
    @Override
    public void containShopData(final OnCallBack onCallBack) {
        OkGo.<String>get(Aplis.SHOPPINGCART_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responesData = response.body().toString();
                Gson gson = new Gson();
                ShoppingBean shoppingBean = gson.fromJson(responesData, ShoppingBean.class);
                onCallBack.setData(shoppingBean);
            }
        });
    }
}
