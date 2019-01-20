package com.example.banner;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
