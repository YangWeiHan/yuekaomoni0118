package com.example.mryang.yuekaomoni0118.di.contrain;

import com.example.mryang.yuekaomoni0118.data.bean.NewsBean;

public interface IContainNews {

    public interface INewsView{
        void getNewsData(NewsBean newsBean);
    }

    public interface INewsPresenter<INewsView>{

        void attahView(INewsView iNewsView);

        void detachView(INewsView iNewsView);

        void goToRequstNewsData(int page);
    }

    public interface INewsModel{

        void containNewsData(int page,OnCallBack onCallBack);

        public interface OnCallBack{
            void callBack(NewsBean newsBean);
        }
    }
}

