package com.example.gaope.slidingconflicted;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by gaope on 2018/7/21.
 */

public class HoriTopView extends ViewGroup {

    private static final String TAG = "HoriTopView";
    private Scroller scroller;
    private int touchSlop;
    private int downX;
    private int indownX;
    private int indownY;
    private int x1;
    private int x;
    private int y;

    /**
     * 左边界
     * @param context
     */
    private int leftBroad;

    /**
     * 右边界
     * @param context
     */
    private int rightBroad;

    public HoriTopView(Context context) {
        super(context);
    }

    public HoriTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(getContext());
        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public HoriTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for(int i =0 ;i < childCount; i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int childCount = getChildCount();
            for(int i = 0; i< childCount; i++){
                View childView = getChildAt(i);
                /// Log.d(TAG,"getMeas:"+childView.getMeasuredWidth());
                childView.layout(i * childView.getMeasuredWidth(),0,(i + 1) * childView.getMeasuredWidth(),childView.getMeasuredHeight());
            }
            leftBroad = getChildAt(0).getLeft();
            rightBroad = getChildAt(getChildCount() - 1).getRight();

        }
    }

    //外部拦截法解决类似于ViewPager嵌套ScrollView的不同方向的滑动冲突
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        x = (int) ev.getX();
//        y = (int) ev.getY();
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                indownX = x;
//                indownY = y;
//                return false;
//            case MotionEvent.ACTION_MOVE:
//                int dx = (int) (x - indownX);
//                int dy = (int) (y - indownY);
//                Log.d(TAG,"aax:"+x);
//                Log.d(TAG,"aay:"+y);
//                Log.d(TAG,"aaindownx:"+indownX);
//                Log.d(TAG,"aaindowny:"+indownY);
//                Log.d(TAG,"aadx:"+Math.abs(dx));
//                Log.d(TAG,"aady:"+dy);
//                indownX = x;
//                indownY = y;
//                if((Math.abs(dx) > Math.abs(dy)) ){
//                    Log.d("aaa",TAG);
//                    return true;
//                }else {
//                    return false;
//                }
//        }
//
//        return super.onInterceptTouchEvent(ev);
//    }

    //内部拦截法解决类似于ViewPager嵌套ScrollView的不同方向的滑动冲突
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int dx;
        if (x == 0){
            x = (int) event.getRawX();
        }
        downX = x;
        Log.d(TAG,"ccdownx:" + downX);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"nnnn");
                downX = x1;
                return true;
            case MotionEvent.ACTION_MOVE:
                x = (int) event.getRawX();
                dx =  downX - x;
                downX = x;
                Log.d(TAG,"ccx:" + x );
                Log.d(TAG,"ccdx:" + dx );

                Log.d(TAG,"left:" + leftBroad);
                Log.d(TAG,"right:" + rightBroad);
                Log.d(TAG,"getScrollX:" + getScrollX() );
                float aa = getScaleX() + getWidth() + dx;
                Log.d(TAG,"rrrrr:" + aa );

                if (getScrollX() + dx < leftBroad){
                    scrollTo(leftBroad,0);
                    Log.d(TAG,"eeeeeee");
                    return true;
                }else if ( getScrollX() + getWidth() + dx  > rightBroad){
                    //    Log.d(TAG,"width:"+getWidth());
                    Log.d(TAG,"ddddddd");
                    scrollTo(rightBroad - getWidth() , 0);
                    return true;
                }
//                downX = x1;
                scrollBy(dx,0);
                return true;
            case MotionEvent.ACTION_UP:
                //判断在第几个界面
                //加上getWidth()/2使得target的值为大于等于1
                int target = ( getScrollX() + getWidth()/2 )/getWidth();
                //dx
                int dxx = (int) (target * getWidth()) - getScrollX();
                scroller.startScroll(getScrollX() , 0 , dxx , 0);
                invalidate();
                downX = 0;
                x = 0;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo( scroller.getCurrX() , scroller.getCurrY() );
            invalidate();
        }
    }
}
