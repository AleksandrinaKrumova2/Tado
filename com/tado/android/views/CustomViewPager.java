package com.tado.android.views;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager implements OnGestureListener {
    private GestureDetectorCompat detector;
    private boolean longPressed = false;

    public CustomViewPager(Context context) {
        super(context);
        init();
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.detector = new GestureDetectorCompat(getContext(), this);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        this.detector.onTouchEvent(event);
        if (event.getAction() == 1) {
            this.longPressed = false;
        }
        if (this.longPressed) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    public boolean onDown(MotionEvent e) {
        return true;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public void onLongPress(MotionEvent event) {
        this.longPressed = true;
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    public void onShowPress(MotionEvent e) {
    }

    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
}
