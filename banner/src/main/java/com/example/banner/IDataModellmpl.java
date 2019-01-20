package com.example.banner;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IDataModellmpl implements IDataContrain.IDataModel {
    @Override
    public void contrainData(final OnCallBack onCallBack) {
        OkGo.<String>get(Apils.DATA_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responesData = response.body().toString();
                Gson gson = new Gson();
                BannerBean bannerBean = gson.fromJson(responesData, BannerBean.class);
                onCallBack.callBack(bannerBean);
            }
        });
    }
}
