package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor;

import androidx.annotation.NonNull;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.WbiManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WbiSortAndSignInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        List<String> namesAndValues = new ArrayList<>();
        if ("GET".equalsIgnoreCase(request.method())) {
            TreeSet<String> names = new TreeSet<String>(url.queryParameterNames());
            for (String name : names) {
                List<String> values = url.queryParameterValues(name);
                for (String value : values) {
                    namesAndValues.add(String.format("%s=%s", name, value));
                }
            }
        } else if ("POST".equalsIgnoreCase(request.method())) {
            if (request.body() instanceof FormBody) {
                FormBody formBody = (FormBody) request.body();
                TreeMap<String, String> bodyTree = new TreeMap<>();
                // 先复制原来的参数
                for (int i = 0; i < formBody.size(); i++) {
                    bodyTree.put(formBody.encodedName(i), formBody.encodedValue(i));
                }
                for (String key : bodyTree.keySet()) {
                    namesAndValues.add(String.format("%s=%s", key, bodyTree.get(key)));
                }
            }
        }
        String queryString = "";
        for (int i = 0; i < namesAndValues.size(); i++) {
            if (i == namesAndValues.size() - 1) {
                queryString = String.format("%s%s", queryString, namesAndValues.get(i));
            } else {
                queryString = String.format("%s%s&", queryString, namesAndValues.get(i));
            }
        }
        queryString = WbiManager.getInstance().signWithWbi(queryString);
        if (request.method().equalsIgnoreCase("post")) {
            return chain.proceed(
                    request.newBuilder()
                            .url(url.newBuilder()
                                    .encodedQuery(null)
                                    .build())
                            .post(RequestBody.create(queryString, MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8")))
                            .build()
            );
        }
        return chain.proceed(
                request.newBuilder()
                        .url(url.newBuilder()
                                .encodedQuery(queryString)
                                .build())
                        .build()
        );
    }
}
