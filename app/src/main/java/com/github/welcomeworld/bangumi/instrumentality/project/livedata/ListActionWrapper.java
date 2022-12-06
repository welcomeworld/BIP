package com.github.welcomeworld.bangumi.instrumentality.project.livedata;

import java.util.List;

public class ListActionWrapper<T> {
    public static final int REFRESH = 0;
    public static final int MORE = 1;
    private final List<T> data;
    private final int action;

    public ListActionWrapper(int action, List<T> data) {
        this.action = action;
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public int getAction() {
        return action;
    }
}
