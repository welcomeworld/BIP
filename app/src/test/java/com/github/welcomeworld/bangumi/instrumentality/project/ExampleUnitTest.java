package com.github.welcomeworld.bangumi.instrumentality.project;

import com.eclipsesource.v8.V8;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.BiliSortAndSignInterceptor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals("jf", BiliSortAndSignInterceptor.getSign("aid=931289831&appkey=1d8b6e7d45233436&autoplay=0&bili_local_id=&build=6210600&buvid=XWB4D15BA2D803A3045A6877CCCDE4E4975FA&c_locale=zh_CN&device=phone&device_id=&device_name=XiaomiRedmi Note 7&device_platform=Android10XiaomiRedmi Note 7&fnval=16&fnver=0&force_host=0&from=7&local_id=XWB4D15BA2D803A3045A6877CCCDE4E4975FA&mobi_app=android&plat=0&platform=android&s_locale=zh_CN&ts=1626408638931"));
    }

    @Test
    public void testJs() {
        V8 runtime = V8.createV8Runtime();
        runtime.setData("hello","helloTest");
        runtime.executeVoidScript("" +
                "var jsVar = 'jsDATA';");
        System.out.println(runtime.getString("hello"));
        System.out.println(runtime.getString("jsVar"));
        runtime.release(true);
    }

    @Test
    public void testAge() {
        try {
            Connection cookieConn = Jsoup.connect("https://www.agefans.vip/play/20210066?playid=2_1");
            Connection.Response cookieResponse = cookieConn.execute();
            List<String> cookies = cookieResponse.headers("set-cookie");
            HashMap<String, String> requestCookie = new HashMap<>();
            for (String cookie : cookies) {
                requestCookie.put(cookie.substring(0, cookie.indexOf("=")), cookie.substring(cookie.indexOf("=") + 1, cookie.indexOf(";")));
            }
            String baseUrl = "https://www.agefans.vip";
            String playUrl = baseUrl + "/_getplay?aid=" + "20210066" + "&playindex=" + "2" + "&epindex=" + "1" + "&r=" + Math.random();
            System.out.println("AgeParseVideo playUrl:" + playUrl);
            Connection playConn = Jsoup.connect(playUrl);
            playConn.cookie("username", "admin");
            playConn.referrer(baseUrl + "/play/" + "20210066" + "?playid=" + "2" + "_" + "1");
            long cookieT1 = Long.parseLong(requestCookie.get("t1"));
            String k1 = requestCookie.get("k1");
            long t1 = Math.round(cookieT1 / 1000) >> 5;
            long k2 = ((t1 * (t1 % 4096) * 0x3 + 83215) * (t1 % 4096) + t1);
            long t2 = System.currentTimeMillis();
            System.out.println("AgeParseVideo:cookie:start t2=" + t2);
            long k2End = k2 % 10;
            System.out.println("AgeParseVideo:cookie:start k2End=" + k2End);
            long t2End = t2 % 1000;
            System.out.println("AgeParseVideo:cookie:start t2End=" + t2End);
            if (!String.valueOf(t2End).contains("" + k2End)) {
                t2End = t2End % 10;
                System.out.println("AgeParseVideo:cookie:end t2End=" + t2End);
                if (t2End > k2End) {
                    t2 = t2 - (t2End - k2End);
                } else {
                    t2 = t2 + (k2End - t2End);
                }
            }
            System.out.println("AgeParseVideo:cookie:k1=" + k1);
            System.out.println("AgeParseVideo:cookie:t1=" + cookieT1);
            System.out.println("AgeParseVideo:cookie:k2=" + k2);
            System.out.println("AgeParseVideo:cookie:t2=" + t2);
            playConn.cookie("t1", "" + cookieT1);
            playConn.cookie("k1", k1);
            playConn.cookie("k2", "" + k2);
            playConn.cookie("t2", "" + t2);
            long fa_t = System.currentTimeMillis();
            System.out.println("AgeParseVideo:cookie:fa_t=" + fa_t);
            playConn.cookie("fa_t", "" + fa_t);
            playConn.cookie("fa_c", "1");
            Connection.Response playResponse = playConn.execute();
            if (playResponse.statusCode() != 200) {
                System.out.println("AgeParseVideo:play response code:" + playResponse.statusCode());
                return;
            }
            String responseBody = playResponse.body();
            System.out.println("AgeParseVideo:play response:" + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}