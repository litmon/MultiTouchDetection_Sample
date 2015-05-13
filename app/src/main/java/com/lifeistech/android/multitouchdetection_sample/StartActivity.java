package com.lifeistech.android.multitouchdetection_sample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class StartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startTapActivity(View v){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void startListActivity(View v){
        startActivity(new Intent(this, DoubleFingerTapListActivity.class));
    }

    public void startSwipeListActivity(View v){
        startActivity(new Intent(this, SwipeListViewActivity.class));
    }

}
