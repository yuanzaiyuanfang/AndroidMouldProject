package com.brilliant;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;

import com.basemodule.base.BaseApplication;
import com.blankj.utilcode.utils.AndroidUtilsCode;
import com.blankj.utilcode.utils.CrashUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.brilliant.api.RetrofitService;
import com.brilliant.injector.components.ApplicationComponent;
import com.brilliant.injector.components.DaggerApplicationComponent;
import com.brilliant.injector.modules.ApplicationModule;
import com.brilliant.local.BridgeFactory;
import com.brilliant.local.BridgeLifeCycleSetKeeper;
import com.brilliant.local.Bridges;
import com.brilliant.local.dao.NewsTypeDao;
import com.brilliant.local.sharePref.EBSharedPrefManager;
import com.brilliant.local.table.DaoMaster;
import com.brilliant.local.table.DaoSession;
import com.brilliant.rxbus.RxBus;
import com.brilliant.utils.Constants;
import com.brilliant.utils.NativeUtil;
import com.orhanobut.logger.Logger;
import com.taobao.android.SophixManager;
import com.taobao.android.listener.PatchLoadStatusListener;
import com.taobao.android.util.PatchStatus;

import org.greenrobot.greendao.database.Database;

import static com.antfortune.freeline.FreelineCore.getApplication;

/**
 * description:
 * Date: 2017/3/21 16:01
 * User: Administrator
 */
@SuppressWarnings("unused")
public class AndroidAPP extends BaseApplication {

    private static final String DB_NAME = "news-db";

    private static ApplicationComponent sAppComponent;

    private DaoSession mDaoSession;

    public static EBSharedPrefManager ebSharedPrefManager;

    // 因为下载那边需要用，这里在外面实例化在通过 ApplicationModule 设置
    private RxBus mRxBus = new RxBus();

    private static final int REQUEST_EXTERNAL_STORAGE_PERMISSION = 0;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = NativeUtil.getCurProcessName(getApplicationContext());
        if (!StringUtils.isEmpty(processName) && processName.equals(Constants.PACKAGE_NAME)) {
            //=== 工具类库
            AndroidUtilsCode.init(getContext()); // AndroidUtilsCode工具类初始化
            //=== 缓存类初始化
            BridgeFactory.init(this);
            BridgeLifeCycleSetKeeper.getInstance().initOnApplicationCreate(this);
            ebSharedPrefManager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);
            //  _initDatabase();
            _initInjector();
            _initConfig();
        }
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
            // LeakCanary.install(getApplication());
            CrashUtils.getInstance().init();
            LogUtils.getBuilder().setTag("MyTag").setLog2FileSwitch(true).create();
        }
        RetrofitService.init();
        ToastUtils.init(getApplication());
//        DownloaderWrapper.init(mRxBus, mDaoSession.getVideoInfoDao());
//        FileDownloader.init(getApplication());
//        DownloadConfig config = new DownloadConfig.Builder()
//                .setDownloadDir(PreferencesUtils.getSavePath(getApplication()) + File.separator + "video/").build();
//        FileDownloader.setConfig(config);
    }

    /**
     * Hotfix 热修复
     * initialize的调用应该尽可能的早. 强烈推荐在Application.onCreate()中进行SDk初始化以及查询服务器是否有可用补丁的操作.
     */
    private void _initHotFix() {
        SophixManager.getInstance().setContext(this)
                .setAppVersion(getPackageInfo().versionName)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onload(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Logger.i("HotFixManager--补丁加载成功");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                            Logger.i("HotFixManager--新补丁生效需要重启. 业务方可自行实现逻辑, 提示用户或者强制重启, 可以监听应用进入后台事件, 然后应用自杀");
//                            NativeUtil.restartApp(getApplicationContext());
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            // SophixManager.getInstance().cleanPatches();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            Logger.i("HotFixManager--其它信息");
                        }
                    }
                }).initialize();
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}