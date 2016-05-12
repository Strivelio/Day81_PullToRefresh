package com.example.administrator.day81_pulltorefresh.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {

    private HttpUtils() {
    }

    public interface OnLoadCallBack {

        void progress(int progress);
    }

    private static OnLoadCallBack mOnLoadCallBack = null;

    public static void setmOnLoadCallBack(OnLoadCallBack onLoadCallBack) {
        mOnLoadCallBack = onLoadCallBack;
    }

    public static byte[] loadByte(String urlStr) {
        ByteArrayOutputStream baos = null;
        InputStream is = null;
        try {
            baos = new ByteArrayOutputStream();
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();// ����������
            if (code == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                int maxLen = conn.getContentLength();
                byte b[] = new byte[1024];
                int sum = 0;
                int len = 0;
                while ((len = is.read(b)) > 0) {
                    baos.write(b, 0, len);
                    sum += len;
                    if (mOnLoadCallBack != null) {
                        mOnLoadCallBack.progress((int) (100.0 * sum / maxLen));
                    }
                }
                return baos.toByteArray();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.flush();
                baos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

}
