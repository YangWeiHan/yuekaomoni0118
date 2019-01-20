package com.example.mryang.yuekaomoni0118.di.okhttpuilt;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OKHttpUtil {
    private static OKHttpUtil okHttpUtil;

    private final OkHttpClient client;
    //单例模式
    //1、私有化构造函数
    //作用：不能被其他外部内容访问
    private OKHttpUtil(){
        //创建OKHttp对象
        client = new OkHttpClient
                .Builder()
                .addInterceptor(new LogInterceptor())
                .build();
    }
    //2、提供公有方法供外部类访问
    public static OKHttpUtil getInstance(){
        //DCL模式的懒汉式
        if (okHttpUtil == null){
            synchronized (OKHttpUtil.class){
                if (null == okHttpUtil){
                    okHttpUtil = new OKHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }
    //get 异步分装
    public void getEnqueue(String urlString, Callback callback){
        Request request = new Request.Builder().url(urlString).build();
        client.newCall(request).enqueue(callback);
    }
    //post  异步分装
    public void postEnqueue(RequestBody requestBody,String urlString,Callback callback){
        //①在这里首先需要请求体，但是需要具体类进行提供，所以把RequestBody作为参数进行传入
        //②然后需要请求地址参数，也需要具体类进行提供，所以也把URL作为参数进行传入
        Request request = new Request.Builder()
                .method("POST",requestBody)
                .url(urlString)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

}
