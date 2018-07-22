package com.example.gaope.slidingconflicted;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by gaope on 2018/7/21.
 */

public class TwoScrollView extends ScrollView {

    public TwoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
