package com.example.mryang.yuekaomoni0118.ui.actifity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mryang.yuekaomoni0118.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends AppCompatActivity {
    @BindView(R.id.erjiliandong)
    Button erjiliandong;
    @BindView(R.id.liushibuju)
    Button liushibuju;
    @BindView(R.id.shopping_car)
    Button shoppingCar;
    @BindView(R.id.more_item)
    Button moreItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.erjiliandong, R.id.liushibuju, R.id.shopping_car,R.id.more_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.erjiliandong:
                startActivity(new Intent(ShowActivity.this, LinkageEffectActivity.class));
                break;
            case R.id.liushibuju:
                startActivity(new Intent(ShowActivity.this, FlowActivity.class));
                break;
            case R.id.more_item:
                startActivity(new Intent(ShowActivity.this,MoreItemActivity.class));
                break;
            case R.id.shopping_car:
                startActivity(new Intent(ShowActivity.this,ShopCarActivity.class));
                break;

        }
    }

}
