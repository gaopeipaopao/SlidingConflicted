package com.example.gaope.slidingconflicted;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by gaope on 2018/7/21.
 */

public class OneScrollView extends ScrollView {
    private static final String TAG = "OneScrollView";
    private int y;
    private int lastY;
    private int lenY;

    public OneScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    //内部拦截法
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int dy = 0;
        lastY = y;
        //Log.d(TAG,"lastY:"+lastY);
        y = (int) ev.getY();
        //Log.d(TAG,"y:"+y);


        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"abab");
                lastY = y;
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            case MotionEvent.ACTION_MOVE:
                dy = lastY - y;
                lenY = lenY + dy;
                Log.d(TAG,"bby:"+y);
                Log.d(TAG,"bbdy:"+dy);
                Log.d(TAG,"bblenY"+lenY);
                Log.d(TAG,"getScrollY():"+getScrollY());
                if (dy <= 0 && getScrollY() <= 0 ){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }


        return super.onTouchEvent(ev);
    }
}
