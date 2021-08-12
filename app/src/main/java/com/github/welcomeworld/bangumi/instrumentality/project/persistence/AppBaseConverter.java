package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import androidx.room.TypeConverter;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class AppBaseConverter {
    @TypeConverter
    public String List2String(List<VideoListBean> data){
        return new Gson().toJson(data);
    }

    @TypeConverter
    public List<VideoListBean> String2List(String data){
        return new Gson().fromJson(data,new TypeToken<List<VideoListBean>>(){}.getType());
    }
}
