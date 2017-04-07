package com.brilliant;

import android.content.Context;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.brilliant.base.BaseApplication;
import com.brilliant.constant.APPConstant;
import com.brilliant.constant.APPMethod;
import com.brilliant.injector.components.ApplicationComponent;
import com.brilliant.rxbus.RxBus;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * description:
 * Date: 2017/3/21 16:01
 * User: Administrator
 */
@SuppressWarnings("unused")
public class AndroidAPP extends BaseApplication {

    //#################################################################### 自定义变量 start

    private static ApplicationComponent sAppComponent;

    // 因为下载那边需要用，这里在外面实例化在通过 ApplicationModule 设置
    private RxBus mRxBus = new RxBus();

    private RefWatcher refWatcher;

    //#################################################################### 自定义变量 end

    //#################################################################### 重写系统方法 start

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = APPMethod.getCurProcessName(getApplicationContext());
        if (!StringUtils.isEmpty(processName) && processName.equals(APPConstant.PACKAGE_NAME)) {
            _initConfig();
        }

        //=== 内存泄露检测框架
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    //#################################################################### 重写系统方法 end

    //#################################################################### 重写自定义方法 start

    //#################################################################### 重写自定义方法 end

    //#################################################################### 自定义方法 start

    public static AndroidAPP getInstance() {
        return (AndroidAPP) getAppInstance();
    }

    /**
     * 使用Tinker生成Application，这里改成静态调用
     *
     * @return
     */
    public static ApplicationComponent getAppComponent() {
        return sAppComponent;
    }


    /**
     * 初始化配置
     */
    private void _initConfig() {
        if (BuildConfig.DEBUG) {
            //=== okgo
            initOkGo(APPConstant.RESP_SUCCESS_CODE, APPConstant.RESP_NOT_LOGIN, APPConstant.RESP_LOGIN_OTHER_DEVICE,
                    null, null);
            //=== ali httpdns
            initHttpDns(APPConstant.ALI_HTTPDNS_ACCOUND_ID);
        }
        ToastUtils.init(true);
    }

    /**
     * leakcanary
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        AndroidAPP application = (AndroidAPP) context.getApplicationContext();
        return application.refWatcher;
    }

    //#################################################################### 自定义方法 end

}