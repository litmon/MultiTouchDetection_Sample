package com.lifeistech.android.multitouchdetection_sample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    View detectView;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectView = findViewById(R.id.detectView);
        resultView = (TextView)findViewById(R.id.detectResultTextView);

        detectView.setOnTouchListener(mDetectListener);
    }

    View.OnTouchListener mDetectListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            writeLog("actionPointerCount: " + event.getPointerCount());
            writeLog("actionIndex: " + event.getActionIndex());
            writeLog("actionName: " + actionToString(event.getAction()));

            return true;
        }

        public String actionToString(int action) {
            switch (action) {

                case MotionEvent.ACTION_DOWN: return "Down";
                case MotionEvent.ACTION_MOVE: return "Move";
                case MotionEvent.ACTION_POINTER_DOWN: return "Pointer Down";
                case MotionEvent.ACTION_UP: return "Up";
                case MotionEvent.ACTION_POINTER_UP: return "Pointer Up";
                case MotionEvent.ACTION_OUTSIDE: return "Outside";
                case MotionEvent.ACTION_CANCEL: return "Cancel";
            }
            return "";
        }
    };

    public void writeLog(String content){
        Log.d(getClass().getSimpleName(), content);
    }
}
