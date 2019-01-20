package com.example.mryang.yuekaomoni0118.di.presenter;

import com.example.mryang.yuekaomoni0118.data.bean.BusionessBean;
import com.example.mryang.yuekaomoni0118.data.bean.GoodsBean;
import com.example.mryang.yuekaomoni0118.di.contrain.IContrainBusioness;
import com.example.mryang.yuekaomoni0118.di.model.IBusionessModellmpl;

import java.lang.ref.SoftReference;

public class IBusionessPresenterlmpl implements IContrainBusioness.IBusionessPresenter<IContrainBusioness.IBusionessView> {
    IContrainBusioness.IBusionessView iBusionessView;
    private SoftReference<IContrainBusioness.IBusionessView> reference;
    private IBusionessModellmpl modellmpl;

    @Override
    public void attchView(IContrainBusioness.IBusionessView iBusionessView) {
        this.iBusionessView = iBusionessView;
        reference = new SoftReference<>(iBusionessView);
        modellmpl = new IBusionessModellmpl();
    }

    @Override
    public void detachView(IContrainBusioness.IBusionessView iBusionessView) {
        reference.clear();
    }

    @Override
    public void goTORequestData() {
        modellmpl.contrainModel(new IContrainBusioness.IBusionessModel.OnCallback() {
            @Override
            public void callback(BusionessBean busionessBean) {
                iBusionessView.setBusiessData(busionessBean);
            }
        });
    }

    @Override
    public void goTORequestDetilesData(int cid) {
        modellmpl.contrainGoodsModel(cid, new IContrainBusioness.IBusionessModel.OnGoodsCallBack() {
            @Override
            public void onCallback(GoodsBean goodsBean) {
                iBusionessView.setGoodsData(goodsBean);
            }
        });
    }


}
