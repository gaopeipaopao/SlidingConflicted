package com.example.gaope.slidingconflicted;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 横向和竖向两个不同方向的滑动冲突
 * HoriTopView是实现类似于ViewPager的自定义View
 * 它的里面内容是ScrollView
 * 这个时候就产生了滑动冲突
 */

public class DiffDirectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_direction);
    }
}
