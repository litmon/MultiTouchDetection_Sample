package com.lifeistech.android.multitouchdetection_sample;

import android.view.View;
import android.widget.TextView;

/**
 * Created by fukuo on 2015/05/09.
 */
public class Item {
    String content;

    public Item(String content){
        this.content = content;
    }

    public void bind(View v){
        ((TextView)v.findViewById(R.id.content)).setText(content);
    }
}
