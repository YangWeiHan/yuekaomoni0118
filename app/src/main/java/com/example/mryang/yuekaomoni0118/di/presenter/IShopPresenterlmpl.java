package com.example.mryang.yuekaomoni0118.di.presenter;

import com.example.mryang.yuekaomoni0118.data.bean.ShoppingBean;
import com.example.mryang.yuekaomoni0118.di.contrain.IContainShop;
import com.example.mryang.yuekaomoni0118.di.model.IShopModellmpl;

import java.lang.ref.SoftReference;

public class IShopPresenterlmpl implements IContainShop.IShopPresenter<IContainShop.IShopView> {
    IContainShop.IShopView iShopView;
    private SoftReference<IContainShop.IShopView> reference;
    private IShopModellmpl modellmpl;

    @Override
    public void attahView(IContainShop.IShopView iShopView) {
        this.iShopView = iShopView;
        reference = new SoftReference<>(iShopView);
        modellmpl = new IShopModellmpl();
    }

    @Override
    public void detachView(IContainShop.IShopView iShopView) {
        reference.clear();
    }

    @Override
    public void goTORequestData() {
        modellmpl.containShopData(new IContainShop.IShopModel.OnCallBack() {
            @Override
            public void setData(ShoppingBean shoppingBean) {
                iShopView.setShopData(shoppingBean);
            }
        });
    }
}
