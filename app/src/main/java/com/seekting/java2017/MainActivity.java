package com.seekting.java2017;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.seekting.libanno.MyAnno;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @MyAnno(layoutId = 0, viewHolder = "a", viewType = 0)
    public void test() {
    }
}
