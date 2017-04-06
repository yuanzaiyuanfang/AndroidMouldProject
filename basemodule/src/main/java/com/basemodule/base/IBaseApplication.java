package com.basemodule.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.basemodule.utils.NativeUtil;
import com.orhanobut.hawk.Hawk;

/**
 * description:
 * Date: 2016/11/4 09:33
 * User: Administrator
 */
public class IBaseApplication extends MultiDexApplication {

    private static Context _context;

    private static IBaseApplication baseApplication;

    private static PackageInfo packageInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        //init Hawk
        Hawk.init(this).build();
        setContext(getApplicationContext());
        // 安装包信息
        setPackageInfo(NativeUtil.getPackageInfo(getContext()));
    }

    public static synchronized IBaseApplication context() {
        return (IBaseApplication) _context;
    }

    public static Resources getAppResources() {
        return baseApplication.getResources();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 分包
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //================================ getter and setter start ====================================

    public static Context getContext() {
        return _context;
    }

    public static void setContext(Context _context) {
        IBaseApplication._context = _context;
    }

    public static PackageInfo getPackageInfo() {
        if (packageInfo == null) {
            packageInfo = NativeUtil.getPackageInfo(getContext());
        }
        return packageInfo;
    }

    public static void setPackageInfo(PackageInfo packageInfo) {
        IBaseApplication.packageInfo = packageInfo;
    }

    public static Application getAppContext() {
        return baseApplication;
    }

    //================================ getter and setter end ====================================


}
