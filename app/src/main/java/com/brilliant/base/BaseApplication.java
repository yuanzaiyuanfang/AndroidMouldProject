package com.brilliant.base;

import android.content.Context;

import com.basemodule.base.IBaseApplication;

/**
 * add your personal code here
 * description:
 * Date: 2017/4/6 16:56
 * User: Administrator
 */
public class BaseApplication extends IBaseApplication {

    //#################################################################### 自定义变量 start

    //#################################################################### 自定义变量 end

    //#################################################################### 重写系统方法 start

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    //#################################################################### 重写系统方法 end

    //#################################################################### 重写自定义方法 start

    //#################################################################### 重写自定义方法 end

    //#################################################################### 自定义方法 start


    //#################################################################### 自定义方法 end
}
