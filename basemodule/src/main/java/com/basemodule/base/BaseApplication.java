package com.basemodule.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.multidex.MultiDexApplication;

import com.basemodule.utils.NativeUtil;

/**
 * description:
 * Date: 2016/11/4 09:33
 * User: Administrator
 */
public class BaseApplication extends MultiDexApplication {

    private static Context _context;

    private static PackageInfo packageInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());
        // 安装包信息
        setPackageInfo(NativeUtil.getPackageInfo(getContext()));
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }

    //================================ getter and setter start ====================================

    public static Context getContext() {
        return _context;
    }

    public static void setContext(Context _context) {
        BaseApplication._context = _context;
    }

    public static PackageInfo getPackageInfo() {
        if (packageInfo == null) {
            packageInfo = NativeUtil.getPackageInfo(getContext());
        }
        return packageInfo;
    }

    public static void setPackageInfo(PackageInfo packageInfo) {
        BaseApplication.packageInfo = packageInfo;
    }

    //================================ getter and setter end ====================================


}
