package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.CookieInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.interceptor.UserAgentInterceptor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadUtil {
    private static OkHttpClient okHttpClient;
    private static final Map<String, List<DownloadPair<Long>>> downloadTasksRange = new HashMap<>();
    private static final Map<String, List<Call>> downloadCalls = new HashMap<>();


    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (DownloadUtil.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new UserAgentInterceptor())
                            .addInterceptor(new CookieInterceptor())
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    public static void setOkHttpClient(OkHttpClient okHttpClient) {
        DownloadUtil.okHttpClient = okHttpClient;
    }

    public interface DownloadCallback {
        void onPreStart(long contentLength);

        void onSuccess();

        void onProgress(int progress);

        void onFailed();

        void onDownload(long length);
    }

    public static void download(@Nullable String downloadKey, String url, File destFile, DownloadCallback callback) {
        download(downloadKey, url, destFile, null, callback);
    }

    public static void download(@Nullable String downloadKey, String url, File destFile, Map<String, String> headers, DownloadCallback callback) {
        download(downloadKey, getOkHttpClient(), url, destFile, headers, callback);
    }

    public static void download(@Nullable String downloadKey, OkHttpClient okHttpClient, String url, File destFile, Map<String, String> headers, DownloadCallback callback) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        if (downloadKey == null) {
            downloadKey = url;
        }
        LogUtil.e("DownloadUtil", "start download " + downloadKey);
        List<DownloadPair<Long>> downloadRanges = downloadTasksRange.get(downloadKey);
        if (downloadRanges != null) {
            startParallelDownload(downloadKey, requestBuilder, downloadRanges, destFile, callback);
        } else {
            Request downloadRequest = requestBuilder.build();
            String finalDownloadKey = downloadKey;
            okHttpClient.newCall(downloadRequest).enqueue(new Callback() {

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFailed();
                    }
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    ResponseBody body = response.body();
                    if (body == null) {
                        if (callback != null) {
                            callback.onFailed();
                        }
                        return;
                    }
                    long contentLength = body.contentLength();
                    List<DownloadPair<Long>> downloadRanges = new ArrayList<>();
                    LogUtil.e("DownloadUtil", "download all content " + contentLength);
                    if (contentLength > (1024 * 300)) {
                        long step = contentLength / 3;
                        for (int i = 0; i < 3; i++) {
                            if (i + 1 == 3) {
                                downloadRanges.add(new DownloadPair<>(i * step + 1, contentLength - 1));
                            } else if (i == 0) {
                                downloadRanges.add(new DownloadPair<>(0L, (i + 1) * step));
                            } else {
                                downloadRanges.add(new DownloadPair<>(i * step + 1, (i + 1) * step));
                            }
                        }
                    } else {
                        downloadRanges.add(new DownloadPair<>(0L, contentLength));
                    }
                    downloadTasksRange.put(finalDownloadKey, downloadRanges);
                    startParallelDownload(finalDownloadKey, requestBuilder, downloadRanges, destFile, callback);
                    body.close();
                }
            });
        }
    }

    private static void startParallelDownload(String downloadKey, Request.Builder requestBuilder, List<DownloadPair<Long>> downloadRanges, File destFile, DownloadCallback callback) {
        long contentLength = getDownloadLength(downloadRanges);
        if (callback != null) {
            callback.onPreStart(contentLength);
        }
        List<Call> downloadCall = new ArrayList<>();
        for (DownloadPair<Long> range : downloadRanges) {
            LogUtil.e("DownloadUtil", "download " + downloadRanges.indexOf(range) + "with " + range.first + "-" + range.second);
            Request downloadRequest = requestBuilder.removeHeader("RANGE").addHeader("RANGE", "bytes=" + range.first + "-" + range.second).build();
            Call call = okHttpClient.newCall(downloadRequest);
            downloadCall.add(call);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFailed();
                    }
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    ResponseBody body = response.body();
                    if (body == null) {
                        if (callback != null) {
                            callback.onFailed();
                        }
                        return;
                    }
                    InputStream is;
                    RandomAccessFile randomAccessFile = new RandomAccessFile(destFile, "rw");
                    randomAccessFile.seek(range.getFirst());
                    LogUtil.e("DownloadUtil", "download " + downloadRanges.indexOf(range) + "with " + body.contentLength());
                    is = body.byteStream();
                    byte[] buffer = new byte[2048];//缓冲数组2kB
                    int len;
                    long remain = 0;
                    while ((len = is.read(buffer)) != -1) {
                        randomAccessFile.write(buffer, 0, len);
                        range.addFirst(len);
                        remain = getDownloadRemainLength(downloadRanges);
                        if (remain == 0) {
                            if (callback != null) {
                                callback.onDownload(contentLength);
                                callback.onSuccess();
                                downloadTasksRange.remove(downloadKey);
                                downloadCalls.remove(downloadKey);
                                try {
                                    randomAccessFile.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            if (callback != null) {
                                callback.onDownload(contentLength - remain);
                                callback.onProgress((int) ((contentLength - remain) * 100 / contentLength));
                            }
                        }
                    }
                    body.close();
                    LogUtil.e("DownloadUtil", "download success " + downloadRanges.indexOf(range) + "with" + remain);
                }
            });
        }
        downloadCalls.put(downloadKey, downloadCall);
    }

    public static void cancelDownload(@Nullable String downloadKey, String url) {
        if (downloadKey == null) {
            downloadKey = url;
        }
        List<Call> downloadCall = downloadCalls.remove(downloadKey);
        if (downloadCall != null && !downloadCall.isEmpty()) {
            for (Call call : downloadCall) {
                call.cancel();
            }
        }
    }

    private static long getDownloadLength(List<DownloadPair<Long>> downloadRanges) {
        if (downloadRanges.isEmpty()) {
            return 0;
        } else {
            return downloadRanges.get(downloadRanges.size() - 1).getSecond() + 1;
        }
    }

    private static long getDownloadRemainLength(List<DownloadPair<Long>> downloadRanges) {
        if (downloadRanges.isEmpty()) {
            return 0;
        } else {
            int result = 0;
            for (DownloadPair<Long> range : downloadRanges) {
                result += (range.getSecond() + 1 - range.getFirst());
            }
            return result;
        }
    }

    private static final class DownloadPair<S> {
        private Long first;
        private final S second;

        public DownloadPair(Long first, S second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof DownloadPair)) {
                return false;
            }
            DownloadPair<?> p = (DownloadPair<?>) o;
            return Objects.equals(p.first, first) && Objects.equals(p.second, second);
        }

        @Override
        public int hashCode() {
            return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
        }

        @Override
        public String toString() {
            return "DownloadPair{" + first + " " + second + "}";
        }

        public Long getFirst() {
            synchronized (this) {
                return first;
            }
        }

        public void addFirst(long add) {
            synchronized (this) {
                this.first += add;
            }
        }

        public S getSecond() {
            return second;
        }

    }
}
