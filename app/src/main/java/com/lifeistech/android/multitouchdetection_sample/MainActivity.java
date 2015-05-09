package com.lifeistech.android.multitouchdetection_sample;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    View detectView;
    TextView resultView;
    TextView actionNameView;
    TextView movePointView;
    TextView e2ActionNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectView = findViewById(R.id.detectView);
        resultView = (TextView) findViewById(R.id.detectResult);
        actionNameView = (TextView) findViewById(R.id.actionName);
        movePointView = (TextView) findViewById(R.id.movePoint);
        e2ActionNameView = (TextView) findViewById(R.id.e2ActionName);

        detectView.setOnTouchListener(mDetectListener);
        gestureDetector = new GestureDetector(this, gestureListener);
    }

    public void writeLog(String content) {
        Log.d(getClass().getSimpleName(), content);
    }

    View.OnTouchListener mDetectListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int pointerIndex = ((event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT);

            actionNameView.setText("actionName: " + actionToString(event.getActionMasked()));

            if (event.getPointerCount() > 1) {
                writeLog("actionPointerX: " + MotionEventCompat.getX(event, 1));
                writeLog("actionPointerY: " + MotionEventCompat.getY(event, 1));
            }

            actionMasked = event.getActionMasked();

            gestureDetector.onTouchEvent(event);

            return true;
        }
    };

    public int actionMasked;

    GestureDetector gestureDetector;
    GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() {


        @Override
        public boolean onDown(MotionEvent e) {
            resultView.setText("onDown");

            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            resultView.setText("onShowPress");

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            resultView.setText("onSingleTapUp");

            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // resultView.setText("onScroll");
            if (e2.getPointerCount() > 1) {
                resultView.setText("onScroll dX: " + String.format("%.2f", distanceX) + ", dY: " + String.format("%.2f", distanceY));
                movePointView.setText("X: " + e2.getX(1) + ", Y: " + e2.getY(1));

                if (distanceX < -20) {
                    e2ActionNameView.setText("onFling dX: "
                            + String.format("%.2f", distanceX)
                            + ", dY: "
                            + String.format("%.2f", distanceY));
                }
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            resultView.setText("onLongPress");

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            writeLog("onFling e1: " + e1.getX() + ",  " + e1.getY());
            writeLog("onFling e2: " + e2.getX() + ",  " + e2.getY());
            writeLog("onFling velocity: " + velocityX + ", " + velocityY);

            resultView.setText("onFling");

            return false;
        }
    };

    String actionToString(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return "Down";
            case MotionEvent.ACTION_MOVE:
                return "Move";
            case MotionEvent.ACTION_POINTER_DOWN:
                return "Pointer Down";
            case MotionEvent.ACTION_UP:
                return "Up";
            case MotionEvent.ACTION_POINTER_UP:
                return "Pointer Up";
            case MotionEvent.ACTION_OUTSIDE:
                return "Outside";
            case MotionEvent.ACTION_CANCEL:
                return "Cancel";
            default:
                return "";
        }
    }


}
