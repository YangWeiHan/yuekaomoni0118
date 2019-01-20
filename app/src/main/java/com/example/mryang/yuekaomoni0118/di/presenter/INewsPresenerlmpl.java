package com.example.mryang.yuekaomoni0118.di.presenter;

import com.example.mryang.yuekaomoni0118.data.bean.NewsBean;
import com.example.mryang.yuekaomoni0118.di.contrain.IContainNews;
import com.example.mryang.yuekaomoni0118.di.model.INewsModellmpl;

import java.lang.ref.SoftReference;

public class INewsPresenerlmpl implements IContainNews.INewsPresenter<IContainNews.INewsView> {
    IContainNews.INewsView iNewsView;
    private SoftReference<IContainNews.INewsView> reference;
    private INewsModellmpl modellmpl;

    @Override
    public void attahView(IContainNews.INewsView iNewsView) {
        this.iNewsView = iNewsView;
        reference = new SoftReference<>(iNewsView);
        modellmpl = new INewsModellmpl();


    }

    @Override
    public void detachView(IContainNews.INewsView iNewsView) {
        reference.clear();
    }

    @Override
    public void goToRequstNewsData(int page) {
        modellmpl.containNewsData(page,new IContainNews.INewsModel.OnCallBack() {
            @Override
            public void callBack(NewsBean newsBean) {
                iNewsView.getNewsData(newsBean);
            }
        });
    }


}
