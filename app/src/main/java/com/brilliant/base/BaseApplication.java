package com.brilliant.base;

import android.content.Context;

import com.basemodule.base.IBaseApplication;
import com.blankj.utilcode.utils.LogUtils;
import com.brilliant.widget.HttpDNSInterceptor;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.taobao.android.SophixManager;
import com.taobao.android.listener.PatchLoadStatusListener;
import com.taobao.android.util.PatchStatus;

/**
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
        //===
        initOkGo();
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

    /**
     * okgo初始化
     */
    private void initOkGo() {
        //初始化OKGO
        OkGo.init(this);
        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkGo")
                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
//					.setCacheMode(CacheMode.NO_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
//					.setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //如果不想让框架管理cookie,以下不需要
                    .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
//					.setCookieStore(new PersistentCookieStore())       	   	//cookie持久化存储，如果cookie不过期，则一直有效

                    //可以设置https的证书,以下几种方案根据需要自己设置,不需要不用设置
                    .setCertificates()                                                                            //方法一：信任所有证书
//                  .setCertificates(getAssets().open("srca.cer"))     												//方法二：也可以自己设置https证书
//                  .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))			//方法三：传入bks证书,密码,和cer证书,支持双向加密

                    //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
                    .addInterceptor(new HttpDNSInterceptor());
            //这两行同上,不需要就不要传
//					.addCommonHeaders(headers)                               //设置全局公共头
//					.addCommonParams(params);                                //设置全局公共参数
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                            LogUtils.i("HotFixManager--补丁加载成功");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                            LogUtils.i("HotFixManager--新补丁生效需要重启. 业务方可自行实现逻辑, 提示用户或者强制重启, 可以监听应用进入后台事件, 然后应用自杀");
//                            NativeUtil.restartApp(getApplicationContext());
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            // SophixManager.getInstance().cleanPatches();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            LogUtils.i("HotFixManager--其它信息");
                        }
                    }
                }).initialize();
        SophixManager.getInstance().queryAndLoadNewPatch();
    }

    //#################################################################### 自定义方法 end
}
