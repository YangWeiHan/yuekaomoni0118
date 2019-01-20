package com.example.mryang.yuekaomoni0118.ui.actifity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.ui.weigeb.Flowlayout;
/*
* 流式布局
*
* */
public class FlowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        initView();
    }

    private void initView() {
        final Flowlayout flowlayout = findViewById(R.id.flowlayout);
        final EditText  editText = findViewById(R.id.edit_search);
        Button btn_search = findViewById(R.id.btn_search);
        Button btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowlayout.removeAllViews();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(FlowActivity.this);
                textView.setText(editText.getText());
                String stringData = editText.getText().toString();
                textView.setTextColor(Color.BLUE);
                flowlayout.addView(textView);
            }
        });
     }
}
