package com.github.welcomeworld.bangumi.instrumentality.project.ui.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

public class BaseWebView extends WebView {
    public BaseWebView(Context context) {
        super(context);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            ViewParent parent = findViewParentIfNeeds(this);
            if(parent!=null){
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
        return super.onTouchEvent(event);
    }

    private ViewParent findViewParentIfNeeds(View tag){
        ViewParent parent = tag.getParent();
        if(parent == null){
            return null;
        }
        if(parent instanceof ViewPager || parent instanceof AbsListView || parent instanceof ScrollView || parent instanceof HorizontalScrollView){
            return parent;
        }else if(parent instanceof View){
            return findViewParentIfNeeds((View)parent);
        }else {
            return parent;
        }
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        if(clampedX){
            ViewParent parent = findViewParentIfNeeds(this);
            if(parent!=null){
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }
}
