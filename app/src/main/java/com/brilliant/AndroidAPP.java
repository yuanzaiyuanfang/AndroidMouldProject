package com.brilliant;

import android.content.Context;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.brilliant.base.BaseApplication;
import com.brilliant.constant.APPConstant;
import com.brilliant.constant.APPMethod;
import com.brilliant.widget.dialog.CustomProgressDialog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * description:
 * Date: 2017/3/21 16:01
 * User: Administrator
 */
@SuppressWarnings("unused")
public class AndroidAPP extends BaseApplication {

    //#################################################################### 自定义变量 start

    private RefWatcher refWatcher;

    // httpdns预解析域名
    private ArrayList hostList = new ArrayList<>(Arrays.asList("www.aliyun.com", "www.taobao.com"));

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
     * 初始化配置
     */
    private void _initConfig() {
        if (BuildConfig.DEBUG) {
            //
            setProgressDialog(new CustomProgressDialog(getApplicationContext(), R.style.CustomProgressDialog));
            //=== okgo
            initOkGo(null, null);
            //=== ali httpdns
            initHttpDns(APPConstant.ALI_HTTPDNS_ACCOUND_ID, hostList);
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