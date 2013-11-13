package com.android.mobilepos;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {

	private boolean isPagingEnabled;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isPagingEnabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        
        return false;
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = false;
    }

}
