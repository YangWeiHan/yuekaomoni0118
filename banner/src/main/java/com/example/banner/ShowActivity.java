package com.example.banner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.button_1)
    Button button1;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.button_2)
    Button button2;
    @BindView(R.id.button_3)
    Button button3;
    @BindView(R.id.img)
    ImageView img;
    private int request_code = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==request_code){
            if (null!=data){
                Bundle bundle = data.getExtras();
                if (bundle==null){
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS){
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this,"解析结果"+result,Toast.LENGTH_SHORT).show();
                }if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_FAILED){
                    Toast.makeText(this,"解析失败",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @OnClick({R.id.button_1, R.id.button_2, R.id.button_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_1:

                Intent intent=new Intent(ShowActivity.this,CaptureActivity.class);
                startActivityForResult(intent,request_code);
                break;
            case R.id.button_2:
                String text = edit.getText().toString();
                if (TextUtils.isEmpty(text)){
                    Toast.makeText(this,"您输入的为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                edit.setText("");
                Bitmap bitmap = CodeUtils.createImage(text, 400, 400, null);
                img.setImageBitmap(bitmap);

                break;
            case R.id.button_3:
                break;
        }
    }
}
