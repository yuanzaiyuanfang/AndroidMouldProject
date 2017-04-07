package com.brilliant.constant;

/**
 * description:
 * Date: 2017/4/7 11:00
 * User: Administrator
 */
public class APPConstant {

    //=== okgo参数设置
    public static final String RESP_SUCCESS_CODE = "1"; // 请求成功的返回码

    public static final String RESP_NOT_LOGIN = "-1";  // 用户尚未登录的返回码

    public static final String RESP_LOGIN_OTHER_DEVICE = "-2"; //用户在其他设备登录的返回码

    //=== ali httpdns Account ID
    public static final String ALI_HTTPDNS_ACCOUND_ID = "";

    //=== other
    public static final String PACKAGE_NAME = "com.brilliant";  // 应用包名

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

}
