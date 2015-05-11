package com.lifeistech.android.multitouchdetection_sample;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import in.championswimmer.sfg.lib.SimpleFingerGestures;


public class DoubleFingerTapListActivity extends ActionBarActivity {

    ListView listView;
    ArrayAdapter<Item> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_finger_tap_list);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter = new DoubleFingerTapListAdapter(getApplicationContext(), R.layout.layout_list_item));

        adapter.add(new Item("#include<stdio.h>"));
        adapter.add(new Item(""));
        adapter.add(new Item("int main(void){"));
        adapter.add(new Item("printf(\"hello world!\")"));
        adapter.add(new Item("return 0;"));
        adapter.add(new Item("}"));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "onItemClick " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    class DoubleFingerTapListAdapter extends ArrayAdapter<Item> {

        public DoubleFingerTapListAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_item, null);
            }

            getItem(position).bind(convertView);

            SimpleFingerGestures gestures = new SimpleFingerGestures();
            gestures.setDebug(true);
            gestures.setConsumeTouchEvents(true);

            convertView.setOnTouchListener(gestures);
            gestures.setOnFingerGestureListener(new OnSwipeGestureListener((TextView) convertView.findViewById(R.id.content)));

            return convertView;
        }

        class OnSwipeGestureListener implements SimpleFingerGestures.OnFingerGestureListener {

            TextView v;

            public OnSwipeGestureListener(TextView v) {
                this.v = v;
            }

            @Override
            public boolean onSwipeUp(int fingers, long gestureDuration) {
                return false;
            }

            @Override
            public boolean onSwipeDown(int fingers, long gestureDuration) {
                return false;
            }

            @Override
            public boolean onSwipeLeft(int fingers, long gestureDuration) {

                ((View) v.getParent()).setBackgroundColor(Color.rgb(255, 255, 255));

                return false;
            }

            @Override
            public boolean onSwipeRight(int fingers, long gestureDuration) {

                ((View) v.getParent()).setBackgroundColor(Color.rgb(0, 255, 0));

                return false;
            }

            @Override
            public boolean onPinch(int fingers, long gestureDuration) {
                return false;
            }

            @Override
            public boolean onUnpinch(int fingers, long gestureDuration) {
                return false;
            }
        }
    }
}
