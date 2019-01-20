package com.example.banner;

import java.lang.ref.SoftReference;

public class IPresenterlmpl implements IDataContrain.IDataPresenter<IDataContrain.IDataView> {
    IDataContrain.IDataView iDataView;
    private SoftReference<IDataContrain.IDataView> reference;
    private IDataModellmpl modellmpl;

    @Override
    public void attahView(IDataContrain.IDataView iDataView) {
        this.iDataView = iDataView;
        reference = new SoftReference<>(iDataView);
        modellmpl = new IDataModellmpl();
    }

    @Override
    public void detachView(IDataContrain.IDataView iDataView) {
        reference.clear();
    }

    @Override
    public void gotORequestData() {
        modellmpl.contrainData(new IDataContrain.IDataModel.OnCallBack() {
            @Override
            public void callBack(BannerBean bannerBean) {
                iDataView.setViewData(bannerBean);
            }
        });
    }
}
