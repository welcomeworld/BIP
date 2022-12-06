package com.github.welcomeworld.bangumi.instrumentality.project.livedata;

public class DataActionWrapper<T> {
    private static final int NONE = -1;
    public static final int REFRESH = 0;
    public static final int MORE = 1;
    private final int action;
    private final T data;

    public DataActionWrapper(int action, T data) {
        this.action = action;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public int getAction() {
        return action;
    }
}
