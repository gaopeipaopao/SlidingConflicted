package com.example.gaope.slidingconflicted;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by gaope on 2018/7/21.
 */

public class LineView extends LinearLayout {
    private static final String TAG = "LineView";

    private int y;
    private int lastY;
    private int lenY;
    private ScrollView scrollView;


    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //得到LineView下的ScrollView，当ScrollView的getScrollY()==0时，
        // 说明此时向下滑动时，将会有整个LineView都向下滑动
        scrollView = (ScrollView)getChildAt(0);
    }

//    //外部拦截法
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean intercept = false;
//        int scrollviewY = scrollView.getScrollY();
//        int dy = 0;
//        lastY = y;
//        y = (int) ev.getY();
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                lastY = y;
//                return false;
//            case MotionEvent.ACTION_MOVE:
//                dy = lastY - y;
//                if (dy < 0 && scrollviewY == 0){
//                    intercept = true;
//                }else {
//                    intercept = false;
//                }
//                return intercept;
//            case MotionEvent.ACTION_UP:
//                lenY = 0;
//                return false;
//        }
//        return intercept;
//    }

    //内部拦截法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int dy = 0;
        lastY = y;
        Log.d(TAG,"lastY:"+lastY);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                y = (int) event.getY();
                Log.d(TAG,"y:"+y);
                dy = lastY - y;
                Log.d(TAG,"dy:"+dy);
                lenY = lenY + dy;
                Log.d(TAG,"lenY"+lenY);
                scrollBy(0,dy);
                return true;
            case MotionEvent.ACTION_UP:
                scrollTo(0,0);
                //scrollBy(0,-lenY);
                //scrollTo(0,-dy);也能实现回弹效果，但是不知道咋回事
                lenY = 0;
                lastY = 0;
                y = 0;
                Log.d(TAG,"lenYaaa"+lenY);
                return true;
        }

        return true;
    }
}
