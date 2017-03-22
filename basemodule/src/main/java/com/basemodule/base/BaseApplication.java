package com.basemodule.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * description:
 * Date: 2016/11/4 09:33
 * User: Administrator
 */
public class BaseApplication extends MultiDexApplication {

   private static Context _context;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());
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

    //================================ getter and setter end ====================================

}
