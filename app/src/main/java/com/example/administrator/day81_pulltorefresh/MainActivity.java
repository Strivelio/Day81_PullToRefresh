package com.example.administrator.day81_pulltorefresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.day81_pulltorefresh.utils.HttpUtils;
import com.example.administrator.day81_pulltorefresh.utils.ImageLoad;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MainActivity extends AppCompatActivity {

    private PtrClassicFrameLayout pcfl;
    private TextView tv;
    private ListView lv_show;
    private ImageView iv_pic;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        pcfl = (PtrClassicFrameLayout) findViewById(R.id.pcfl);
        tv = (TextView) findViewById(R.id.tv);
        lv_show = (ListView) findViewById(R.id.lv_show);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
       // getHandler();
        beginRefresh();
    }
    private int time;
    //提过一个方法来判断是否下载完成
    private void beginRefresh() {
        pcfl.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ImageLoad.showImage("http://s.qdcdn.com/cl/10083178,800,450.jpg", iv_pic, new HttpUtils.OnLoadCallBack() {

                    @Override
                    public void progress(int progress) {
                        if (progress == 99) {
                            Log.i("TAG", progress + "");
                        }
                    }
                });
                pcfl.refreshComplete();
            }
        });
    }
    private void getHandler() {
        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {

            }
        };
    }
}
