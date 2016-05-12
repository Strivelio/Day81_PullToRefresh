 package com.example.administrator.day81_pulltorefresh.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/** 将下载字节数组转为Bitmap对象工具
 * Created by Administrator on 2016/5/5.
 */
public class ImageLoad {
    public static void showImage(final String Url, final ImageView iv, final HttpUtils.OnLoadCallBack onLoadCallBack) {
        new AsyncTask<Void, Void, byte[]>(){

            @Override
            protected void onPostExecute(byte[] bytes) {
                if (bytes != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    iv.setImageBitmap(bitmap);
                }
            }
            @Override
            protected byte[] doInBackground(Void... params) {
                //通过HttpUtils先下载一个字节数组
                return HttpUtils.loadByte(Url);
            }
        }.execute();

    }
}
