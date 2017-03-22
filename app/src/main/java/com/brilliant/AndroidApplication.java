package com.brilliant;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.basemodule.base.BaseApplication;
import com.blankj.utilcode.utils.ToastUtils;
import com.brilliant.api.RetrofitService;
import com.brilliant.injector.components.ApplicationComponent;
import com.brilliant.injector.components.DaggerApplicationComponent;
import com.brilliant.injector.modules.ApplicationModule;
import com.brilliant.local.dao.NewsTypeDao;
import com.brilliant.local.table.DaoMaster;
import com.brilliant.local.table.DaoSession;
import com.brilliant.rxbus.RxBus;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.greendao.database.Database;

import static com.antfortune.freeline.FreelineCore.getApplication;

/**
 * description:
 * Date: 2017/3/21 16:01
 * User: Administrator
 */
@SuppressWarnings("unused")
public class AndroidApplication extends BaseApplication {

    private static final String DB_NAME = "news-db";

    private static ApplicationComponent sAppComponent;
    private static Context sContext;
    private DaoSession mDaoSession;
    // 因为下载那边需要用，这里在外面实例化在通过 ApplicationModule 设置
    private RxBus mRxBus = new RxBus();

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _initDatabase();
        _initInjector();
        _initConfig();
    }

    /**
     * 使用Tinker生成Application，这里改成静态调用
     * @return
     */
    public static ApplicationComponent getAppComponent() {
        return sAppComponent;
    }

    public static Context getContext() {
        return sContext;
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
     * 初始化数据库
     */
    private void _initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplication(), DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        NewsTypeDao.updateLocalData(getApplication(), mDaoSession);
    }

    /**
     * 初始化配置
     */
    private void _initConfig() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(getApplication());
            Logger.init("LogTAG");
        }
        RetrofitService.init();
        ToastUtils.init(getApplication());
//        DownloaderWrapper.init(mRxBus, mDaoSession.getVideoInfoDao());
//        FileDownloader.init(getApplication());
//        DownloadConfig config = new DownloadConfig.Builder()
//                .setDownloadDir(PreferencesUtils.getSavePath(getApplication()) + File.separator + "video/").build();
//        FileDownloader.setConfig(config);
    }
}