package com.example.banner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.banner.IDataContrain.IDataView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IDataView {

    @BindView(R.id.guide_pager)
    ViewPager guidePager;
    @BindView(R.id.but_start)
    Button butStart;
    private IPresenterlmpl presenterlmpl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenterlmpl = new IPresenterlmpl();
        presenterlmpl.attahView(this);
        presenterlmpl.gotORequestData();
        butStart.setVisibility(View.GONE);
    }

    @Override
    public void setViewData(final BannerBean bannerBean) {
       BannerAdapter bannerAdapter = new BannerAdapter(this,bannerBean.getData());
       guidePager.setAdapter(bannerAdapter);
       guidePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int i, float v, int i1) {
           }
           @Override
           public void onPageSelected(int i) {
               if (i == bannerBean.getData().size() -1){
                   butStart.setVisibility(View.VISIBLE);
               }else {
                   butStart.setVisibility(View.GONE);
               }
           }
           @Override
           public void onPageScrollStateChanged(int i) {
           }
       });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterlmpl.detachView(this);
    }

    @OnClick(R.id.but_start)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this,ShowActivity.class));
    }
}
