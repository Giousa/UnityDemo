package com.giou.unitydemo.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;


/**
 * Created by liuenbao on 1/22/16.
 */
public class AutoCyclingApplication extends Application {

    private static final String TAG = AutoCyclingApplication.class.getSimpleName();
    //全局上下文环境
    private static Context mContext;
    //全局的handler
    private static Handler mHandler;
    //主线程
    private static Thread mMainThread;
    //主线程id
    private static int mMainThreadId;
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "AutoCyclingApplication onCreate");

        mContext = getApplicationContext();

        mHandler = new Handler();

        //MyApplication运行在主线程中,所以拿当前线程对象即可
        mMainThread = Thread.currentThread();

        //主线程id,就是MyApplication(主线程)线程id,获取当前线程id
        mMainThreadId = android.os.Process.myTid();


    }

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

}
