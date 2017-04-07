package com.brilliant;

import android.content.Context;

import com.blankj.utilcode.utils.AndroidUtilsCode;
import com.blankj.utilcode.utils.CrashUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.brilliant.base.BaseApplication;
import com.brilliant.constant.APPConstant;
import com.brilliant.injector.components.ApplicationComponent;
import com.brilliant.injector.components.DaggerApplicationComponent;
import com.brilliant.injector.modules.ApplicationModule;
import com.brilliant.local.BridgeFactory;
import com.brilliant.local.BridgeLifeCycleSetKeeper;
import com.brilliant.local.Bridges;
import com.brilliant.local.sharePref.EBSharedPrefManager;
import com.brilliant.local.table.DaoMaster;
import com.brilliant.local.table.DaoSession;
import com.brilliant.rxbus.RxBus;
import com.brilliant.utils.Constants;
import com.brilliant.utils.NativeUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.greendao.database.Database;

/**
 * description:
 * Date: 2017/3/21 16:01
 * User: Administrator
 */
@SuppressWarnings("unused")
public class AndroidAPP extends BaseApplication {

    //#################################################################### 自定义变量 start

    private static final String DB_NAME = "news-db";

    private static ApplicationComponent sAppComponent;

    private DaoSession mDaoSession;

    public static EBSharedPrefManager ebSharedPrefManager;

    // 因为下载那边需要用，这里在外面实例化在通过 ApplicationModule 设置
    private RxBus mRxBus = new RxBus();

    private RefWatcher refWatcher;

    //#################################################################### 自定义变量 end

    //#################################################################### 重写系统方法 start

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = NativeUtil.getCurProcessName(getApplicationContext());
        if (!StringUtils.isEmpty(processName) && processName.equals(Constants.PACKAGE_NAME)) {
            _initConfig();
            _initDatabase();
            _initInjector();
        }

        //=== 内存泄露检测框架
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    /**
     * 退出应用，清理内存
     */
    private void onDestory() {
        BridgeLifeCycleSetKeeper.getInstance().clearOnApplicationQuit();
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
     * 初始化数据库
     */
    private void _initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
    }

    /**
     * 初始化注射器
     */
    private void _initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据
        sAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, mDaoSession, mRxBus))
                .build();
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
            //=== 工具类库
            AndroidUtilsCode.init(getContext()); // AndroidUtilsCode工具类初始化
            //=== 缓存类初始化
            BridgeFactory.init(this);
            BridgeLifeCycleSetKeeper.getInstance().initOnApplicationCreate(this);
            ebSharedPrefManager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);
            //===
            // 异常捕捉
            CrashUtils.getInstance().init();
            // log日志
            LogUtils.getBuilder().setTag("MyTag").setLog2FileSwitch(true).create();
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