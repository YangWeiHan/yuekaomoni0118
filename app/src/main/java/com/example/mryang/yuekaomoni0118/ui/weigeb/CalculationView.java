package com.example.mryang.yuekaomoni0118.ui.weigeb;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mryang.yuekaomoni0118.R;

public class CalculationView extends LinearLayout implements View.OnClickListener{


    private final Button btn_decrease;
    private final Button btn_add;
    private final TextView tv_number;

    public CalculationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View rootView = LayoutInflater.from(context).inflate(R.layout.calculation_item,this);
        btn_decrease = rootView.findViewById(R.id.btn_decrease);
        btn_add = rootView.findViewById(R.id.btn_add);
        tv_number = rootView.findViewById(R.id.tv_number);

        btn_add.setOnClickListener(this);
        btn_decrease.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String numberString = tv_number.getText().toString();
        int number = Integer.parseInt(numberString);
        switch (v.getId()){
            case R.id.btn_decrease :
                number --;
                if (number < 0){
                    number = 0;
                    tv_number.setText(String.valueOf(number));
                }
                tv_number.setText(String.valueOf(number));
                OnCalculationLisenter.onDecrease(number);
                break;
            case R.id.btn_add :
                number ++;
                tv_number.setText(String.valueOf(number));
                OnCalculationLisenter.onAdd(number);
                break;
        }
    }
    OnCalculationLisenter OnCalculationLisenter;

    public void setOnCalculationLisenter(CalculationView.OnCalculationLisenter onCalculationLisenter) {
        OnCalculationLisenter = onCalculationLisenter;
    }

    public interface OnCalculationLisenter{
        void onDecrease(int number);

        void onAdd(int number);
    }

}
