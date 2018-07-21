package com.example.gaope.slidingconflicted;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 内部与外部的滑动方向相同，竖直方向的滑动冲突
 */

public class LineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
    }
}
