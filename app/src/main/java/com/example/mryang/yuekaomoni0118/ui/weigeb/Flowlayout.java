package com.example.mryang.yuekaomoni0118.ui.weigeb;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class Flowlayout extends LinearLayout {

    //设置子布局中做高的那个
    private int mChildMaxHeight;
    //每一个子布局上下的间距
    private int mVSpace = 20;
    //每一个子布局左右的间距
    private int mHSpace = 20;

    public Flowlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthSize,heightSize);
        findMaxChildHeight();
        int top = 0,left = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (left != 0){
                if ((left + childAt.getMeasuredWidth()) > widthSize){
                    top += mChildMaxHeight+mVSpace;
                    left = 0;
                }
            }
            left += childAt.getMeasuredWidth()+mHSpace;
        }
        setMeasuredDimension(widthSize,(top + mChildMaxHeight) > heightSize ? heightSize : top + mChildMaxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int top = 0 , left = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (left != 0 ){
                if ((left + childAt.getMeasuredWidth()) > getWidth()){
                    top += mChildMaxHeight+mVSpace;
                    left = 0;
                }
            }
            childAt.layout(left,top,left+childAt.getMeasuredWidth(),top+getMeasuredHeight());
            left += childAt.getMeasuredWidth() + mHSpace;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void findMaxChildHeight(){
        mChildMaxHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view.getMeasuredHeight() > mChildMaxHeight){
                mChildMaxHeight = view.getMeasuredHeight();
            }
        }
    }
}
