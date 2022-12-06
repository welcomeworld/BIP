package com.github.welcomeworld.bangumi.instrumentality.project.livedata;

import android.os.Looper;

import androidx.lifecycle.LiveData;

public class SafeLiveData<T> extends LiveData<T> {
    public void updateValueSafe(T value) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            super.postValue(value);
        } else {
            super.setValue(value);
        }
    }
}
