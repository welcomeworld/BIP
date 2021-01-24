package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;

import java.lang.reflect.Field;


public class ToastUtil {

    public static void showToast(CharSequence message) {
        if(Looper.getMainLooper() != Looper.myLooper()){
            return;
        }
        Toast mToast = Toast.makeText(BIPApp.getInstance(),
                message, Toast.LENGTH_SHORT);
        hook(mToast);
        mToast.show();
    }


    public static void showToast(int rId) {
        if(Looper.getMainLooper() != Looper.myLooper()){
            return;
        }
        Toast mToast = Toast.makeText(BIPApp.getInstance(), rId,
                Toast.LENGTH_SHORT);
        hook(mToast);
        mToast.show();
    }

    public static void showToastLong(CharSequence message) {
        if(Looper.getMainLooper() != Looper.myLooper()){
            return;
        }
        Toast mToast = Toast.makeText(BIPApp.getInstance(), message,
                Toast.LENGTH_LONG);
        hook(mToast);
        mToast.show();
    }

    private static class SafelyHandlerWarpper extends Handler {

        private Handler impl;

        public SafelyHandlerWarpper(Handler impl) {
            this.impl = impl;
        }

        @Override
        public void dispatchMessage(Message msg) {
            try {
                super.dispatchMessage(msg);
            } catch (Exception e) {
                //ignore
            }
        }

        @Override
        public void handleMessage(Message msg) {
            impl.handleMessage(msg);//需要委托给原Handler执行
        }
    }

    private static Field sField_TN ;
    private static Field sField_TN_Handler ;
    static {
        try {
            sField_TN = Toast.class.getDeclaredField("mTN");
            sField_TN.setAccessible(true);
            sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
            sField_TN_Handler.setAccessible(true);
        } catch (Exception e) {
            //ignore
        }
    }

    private static void hook(Toast toast) {
        try {
            Object tn = sField_TN.get(toast);
            Handler preHandler = (Handler)sField_TN_Handler.get(tn);
            sField_TN_Handler.set(tn,new SafelyHandlerWarpper(preHandler));
        } catch (Exception e) {
            //ignore
        }
    }

    private static long lastToastTime = 0;

    public static void showCustomToast(int layout) {
        showCustomToast(layout, Gravity.CENTER,0,0);
    }
    public static void showCustomToast(int layout,int gravity, int xOffset, int yOffset) {
        if(Looper.getMainLooper() != Looper.myLooper()|| System.currentTimeMillis()-lastToastTime<1500){
            return;
        }
        lastToastTime = System.currentTimeMillis();
        View toastConentView = LayoutInflater.from(BIPApp.getInstance()).inflate(layout,null);
        Toast mToast = new Toast(BIPApp.getInstance());
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setGravity(gravity,xOffset,yOffset);
        mToast.setView(toastConentView);
        hook(mToast);
        mToast.show();
    }

}
