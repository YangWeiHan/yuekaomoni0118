package com.example.mryang.yuekaomoni0118.di.model;

import com.example.mryang.yuekaomoni0118.data.apils.Aplis;
import com.example.mryang.yuekaomoni0118.data.bean.NewsBean;
import com.example.mryang.yuekaomoni0118.di.contrain.IContainNews;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class INewsModellmpl implements IContainNews.INewsModel {
    @Override
    public void containNewsData(int page,final OnCallBack onCallBack) {
        OkGo.<String>get(Aplis.NEWS_URL+"?page="+page).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(responseData, NewsBean.class);
                onCallBack.callBack(newsBean);
            }
        });
    }
}
