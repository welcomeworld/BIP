package com.github.welcomeworld.bangumi.instrumentality.project.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SwiperefreshContainer extends SwipeRefreshLayout {
    private boolean isLoading;
    private OnLoadListener onLoadListener;
    private int mScaledTouchSlop;
    private float mDownY,mCurrentY;
    private RecyclerView recyclerView;


    public boolean isLoading(){
        return isLoading;
    }

    public void setLoading(boolean isLoading){
        if(isLoading&&!this.isLoading){
            this.isLoading=true;
            if(onLoadListener!=null){
                onLoadListener.onLoad();
            }
        }else{
            this.isLoading=isLoading;
        }
    }


    public SwiperefreshContainer(@NonNull Context context) {
        super(context);
        mScaledTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public SwiperefreshContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaledTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(recyclerView==null){
            if(getChildAt(0)instanceof RecyclerView) {
                recyclerView = (RecyclerView) getChildAt(0);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()== MotionEvent.ACTION_DOWN){
            mDownY=ev.getY();
        }
        if(ev.getAction()== MotionEvent.ACTION_MOVE){
            mCurrentY=ev.getY();
            if(canLoad()){
                setLoading(true);
            }
        }
        if(ev.getAction()== MotionEvent.ACTION_UP){
            mCurrentY=ev.getY();
            if(canLoad()){
                setLoading(true);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public interface OnLoadListener{
        void onLoad();
    }

    private boolean canLoad(){
        if(recyclerView == null){
            return false;
        }
        final int offset = recyclerView.computeVerticalScrollOffset();
        final int range = recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent();
        if (range == 0) return mDownY-mCurrentY>=mScaledTouchSlop&&!isLoading;
        return mDownY-mCurrentY>=mScaledTouchSlop&&!isLoading&&!(offset < range - 450);
    }

    public void setOnLoadListener(OnLoadListener onLoadListener){
        this.onLoadListener=onLoadListener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
