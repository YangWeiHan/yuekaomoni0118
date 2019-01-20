package com.example.banner;

public interface IDataContrain {

    public interface IDataView{
            void setViewData(BannerBean bannerBean);
    }

    public interface IDataPresenter<IDataView>{
        void attahView(IDataView iDataView);
        void detachView(IDataView iDataView);
        void gotORequestData();
    }

    public interface IDataModel{

        void contrainData(OnCallBack onCallBack);

        public interface OnCallBack{
            void callBack(BannerBean bannerBean);
        }

    }
}
