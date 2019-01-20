package com.example.mryang.yuekaomoni0118.di.contrain;

import com.example.mryang.yuekaomoni0118.data.bean.BusionessBean;
import com.example.mryang.yuekaomoni0118.data.bean.GoodsBean;

public interface IContrainBusioness {

    public interface IBusionessView{
        void setBusiessData(BusionessBean busionessBean);
        void setGoodsData(GoodsBean goodsBean);
    }

    public interface IBusionessPresenter<IBusionessView>{

        void attchView(IBusionessView iBusionessView);

        void detachView(IBusionessView iBusionessView);

        void goTORequestData();
        void goTORequestDetilesData(int cid);
    }

    public interface IBusionessModel{

        void contrainModel(OnCallback onCallback);
        void contrainGoodsModel(int cid , OnGoodsCallBack onGoodsCallBack);
        public interface OnCallback{
            void callback(BusionessBean busionessBean);
        }
        public interface OnGoodsCallBack{
            void onCallback(GoodsBean goodsBean);
        }
    }
}
