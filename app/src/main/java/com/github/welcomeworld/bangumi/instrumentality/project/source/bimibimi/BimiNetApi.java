package com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi;

import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class BimiNetApi {
    //http://www.dilidili3.com/public/api.php?app=video&do=search&q=%E8%8A%B1%E7%89%8C%E5%A5%87%E7%BC%98&page=2
    public List<VideoListBean> search(String key, String pn) {
        Document document = Jsoup.parse("http://www.dilidili3.com/public/api.php?app=video&do=search&q=%E8%8A%B1%E7%89%8C%E5%A5%87%E7%BC%98&page=2",10000);

    }
}
