package com.example.yuekao0428.view.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class  ViewActivity extends ViewGroup {

    private int hsize;
    private int wsize;
    private int childw;
    private int childh;

    public ViewActivity(Context context) {
        super(context);
    }

    public ViewActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        
          measureChildren(widthMeasureSpec,heightMeasureSpec);
          int top=10;
          int left=10;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        hsize = MeasureSpec.getSize(heightMeasureSpec);
        wsize = MeasureSpec.getSize(widthMeasureSpec);
        switch (mode){
            case  MeasureSpec.AT_MOST:
                for (int i = 0; i <getChildCount() ; i++) {
                    childw = getChildAt(i).getMeasuredWidth();
                    childh = getChildAt(i).getMeasuredHeight();
                    if (left+ childw +10>wsize){
                        top+=(childw+10);
                        left=10;
                    }
                    left+=(childw+10);
                }
                top+=(childh+10);
                break;
        }
        setMeasuredDimension(wsize,top);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left=10;
        int top=10;
        for (int i = 0; i <getChildCount() ; i++) {
             childw = getChildAt(i).getMeasuredWidth();
             childh = getChildAt(i).getMeasuredHeight();
             if (left+childw>wsize){
                 top+=(childh+10);
                 left=10;
                 getChildAt(i).layout(left,top,left+childw,top+childh);
             }else {
                 getChildAt(i).layout(left,top,left+childw,top+childh);
             }
             left+=(childw+10);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
