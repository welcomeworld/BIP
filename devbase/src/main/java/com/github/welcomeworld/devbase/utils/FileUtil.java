package com.github.welcomeworld.devbase.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    public static String openAssert(Context context, String filepath) {
        try {
            InputStream inputStream = context.getAssets().open(filepath);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
            return outputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static InputStream openAssertStream(Context context, String filepath) {
        try {
            return context.getAssets().open(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
