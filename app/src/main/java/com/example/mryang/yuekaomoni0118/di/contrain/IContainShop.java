package com.example.mryang.yuekaomoni0118.di.contrain;

import com.example.mryang.yuekaomoni0118.data.bean.ShoppingBean;

public interface IContainShop {

    public interface IShopView{
        void setShopData(ShoppingBean shoppingBean);
    }

    public interface IShopPresenter<IShopView>{

        void attahView(IShopView iShopView);

        void detachView(IShopView iShopView);

        void goTORequestData();
    }

    public interface IShopModel{

        void containShopData(OnCallBack onCallBack);

        public interface OnCallBack{
            void setData(ShoppingBean shoppingBean);
        }
    }

}
