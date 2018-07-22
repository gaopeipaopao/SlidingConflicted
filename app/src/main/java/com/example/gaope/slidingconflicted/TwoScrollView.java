package com.example.gaope.slidingconflicted;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by gaope on 2018/7/21.
 */

public class TwoScrollView extends ScrollView {

    private static final String TAG = "TwoScrollView";
    private int indownX;
    private int indownY;
    private int x;
    private int y;

    public TwoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //内部拦截法
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG,"indownx1:"+indownX);
        Log.d(TAG,"indowny1:"+indownY);
        x = (int) ev.getX();
        y = (int) ev.getY();
        int dx;
        int dy;

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"cacaca");
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            case MotionEvent.ACTION_MOVE:
                dx = (int) (x - indownX);
                dy = (int) (y - indownY);
                Log.d(TAG,"x:"+x);
                Log.d(TAG,"y:"+y);
                Log.d(TAG,"indownx:"+indownX);
                Log.d(TAG,"indowny:"+indownY);
                Log.d(TAG,"dx:"+Math.abs(dx));
                Log.d(TAG,"dy:"+dy);
                indownX = x;
                indownY = y;
                if((Math.abs(dx) > Math.abs(dy)) ){
                    Log.d("aaa",TAG);
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
