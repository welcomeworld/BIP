package com.github.welcomeworld.app_update;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.github.welcomeworld.app_update.databinding.AppUpdateDlgUpdateBinding;
import com.github.welcomeworld.devbase.utils.FileUtil;
import com.github.welcomeworld.devbase.utils.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateManager {
    private static WeakReference<Context> weakContext;
    private static OkHttpClient okHttpClient = null;
    private static Retrofit retrofit = null;

    public static void checkUpdate(Context context, String updateUrl, long versionCode) {
        weakContext = new WeakReference<>(context);
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder().build();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://gitee.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        retrofit.create(UpdateNetAPI.class).checkUpdate(updateUrl).enqueue(new Callback<UpdateBean>() {
            @Override
            public void onResponse(@NonNull Call<UpdateBean> call, @NonNull Response<UpdateBean> response) {
                Context context = weakContext.get();
                if (context == null) {
                    return;
                }
                if (response.body() == null) {
                    ToastUtil.showToast(R.string.app_update_update_fail);
                    return;
                }
                if (versionCode >= response.body().getVersionCode()) {
                    ToastUtil.showToast(R.string.app_update_lastest_version);
                } else {
                    AppUpdateDlgUpdateBinding binding = AppUpdateDlgUpdateBinding.inflate(LayoutInflater.from(context));
                    AlertDialog alertDialog = new AlertDialog.Builder(context)
                            .setCancelable(false)
                            .setView(binding.getRoot())
                            .create();
                    alertDialog.show();
                    binding.dialogUpdateCancel.setOnClickListener(v -> alertDialog.dismiss());
                    binding.dialogUpdateOk.setOnClickListener(v -> {
                        alertDialog.dismiss();
                        downloadUpdate(response.body());
                        ToastUtil.showToast(R.string.app_update_update_start);
                    });
                    binding.dialogUpdateContent.setText(response.body().getDesc());
                }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateBean> call, @NonNull Throwable t) {
                ToastUtil.showToast(R.string.app_update_update_fail);
            }
        });
    }

    public static void downloadUpdate(UpdateBean updateBean) {
        Request request = new Request.Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36")
                .url(updateBean.getUrl())
                .build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
                Context context = weakContext.get();
                if (context == null) {
                    return;
                }
                File file = new File(context.getExternalFilesDir(null), updateBean.getVersionName() + ".apk");
                InputStream is = null;
                FileOutputStream fileOutputStream = null;
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    is = response.body().byteStream();
                    fileOutputStream = new FileOutputStream(file, false);
                    byte[] buffer = new byte[2048];//缓冲数组2kB
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                    context = weakContext.get();
                    if (context != null) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setDataAndType(FileUtil.fileToUri(file, context), "application/vnd.android.package-archive");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        context.startActivity(intent);
                    }
                } finally {
                    fileOutputStream.close();
                    is.close();
                }
            }
        });
    }
}
