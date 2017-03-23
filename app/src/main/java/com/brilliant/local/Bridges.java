
package com.brilliant.local;

import com.brilliant.utils.FileUtil;

import java.io.File;


/**
 * <与Bridge模块一一对应，用以在BridgeFactory获取某个Bridge对应的Key>
 *
 * @author caoyinfei
 * @version [版本号, 2016/6/6]
 * @see [相关类/方法]
 * @since [V1]
 */
public class Bridges {

    public static boolean isAsset = false;

    public static final String CACHE_FILE = "/ddjr";

    public static final String UpdateApplication = "/download"; //保存升级APK的目录

    /**
     * 本地缓存(sd卡存储和手机内部存储)
     */
    public static final String LOCAL_FILE_STORAGE = "com.focustech.electronicbrand.LOCAL_FILE_STORAGE";

    /**
     * SharedPreference缓存
     */
    public static final String SHARED_PREFERENCE = "com.focustech.electronicbrand.SHARED_PREFERENCE";

    /**
     * 安全
     */
    public static final String SECURITY = "com.focustech.electronicbrand.SECURITY";

    /**
     * 用户Session
     */
    public static final String USER_SESSION = "com.focustech.electronicbrand.USER_SESSION";

    /**
     * CoreService，主要维护功能模块
     */
    public static final String CORE_SERVICE = "com.focustech.electronicbrand.CORE_SERVICE";


    /**
     * 数据库模块
     */
    public static final String DATABASE = "com.focustech.electronicbrand.DATABASE";

    /**
     * http请求
     */
    public static final String HTTP = "com.focustech.electronicbrand.HTTP";

    // App根目录
    public static final String APP_PATH_ROOT = FileUtil.getRootPath().getAbsolutePath()
            + File.separator
            + "xiangfa"
            + File.separator;

    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = APP_PATH_ROOT
            + "osc_img"
            + File.separator;

    // 默认存放安装包下载的路径
    public final static String DEFAULT_SAVE_FILE_PATH = APP_PATH_ROOT
            + "download"
            + File.separator;

    public final static String APP_NAME = "xiangfajinrong.apk";

    // 默认存放异常日志文件的路径
    public final static String DEFAULT_CRASH_FILE_PATH = APP_PATH_ROOT
            + "crash"
            + File.separator;

    // 默认存放缓存文件的路径
    public final static String DEFAULT_CACHE_FILE_PATH = APP_PATH_ROOT
            + "cache"
            + File.separator;

}
