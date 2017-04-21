package com.brilliant.api;

import java.util.Vector;

/**
 * Created by conan on 2017/2/18.
 */

public class APIConstant {

    // 测试版本的时候使用：true
    // 正式发布版本的时候需要使用：false
    public static final boolean isDebug = true;

    /**
     * 根据当前的模式获取对应的请求头部
     *
     * @return
     */
    public static Vector getRequestUrlHead() {
        Vector vector = new Vector();
        //=== 默认为测试模式

        // sit
        String rootHttp = "http://172.16.88.167";
        String requestUrlHead = rootHttp + ":5959/xiangfa/";
        String shareSDKHead = "http://172.16.88.167:8444/";

        // uat
//        String rootHttp = "http://172.16.88.168";
//        String requestUrlHead = rootHttp + ":5959/xiangfa/";
//        String shareSDKHead = "http://172.16.88.168:8444/";

        //===
        if (!isDebug) {
            //=== 当前为正式模式
            rootHttp = "https://";
            requestUrlHead = rootHttp + "/";
            shareSDKHead = "http://newm.xiangfajr.com/";
            //===
            vector.add(requestUrlHead);
            vector.add(rootHttp);
            vector.add(shareSDKHead);
        } else {
            vector.add(requestUrlHead);
            vector.add(rootHttp);
            vector.add(shareSDKHead);
        }
        return vector;
    }

    public static final String KEY_SPLASH_ADVERTISEMENT = "SPLASH_ADVERTISEMENT";

    /**
     * json request参数
     */
    public static final String PLATFORMTYPE_PARAM = "platform";// 平台类型:android/ios/web
    public static final String APP_VERSION_PARAM = "app_version";//应用版本
    public static final String OS_VERSION_PARAM = "os_version";//操作系统版本
    public static final String PHONE_MODEL_PARAM = "phone_model";//手机型号
    public static final String SERVICE_PARAM = "service_provider";//通讯运营商 1：移动、2：电信、3：联通、4：其他
    public static final String NETWORD_PARAM = "network_type";//网络类型 1：wifi、2：3g、3：gprs、4：其它
    public static final String SESSION_TOKEN_PARAM = "session_token";//session_token
    public static final String APP_CHANNEL_PARAM = "channel";//APP_CHANNEL_PARAM app 渠道
    public static final String APP_SIGN__PARAM = "sign";//sign app 签名信息
    public static final String CLIENT_IP_PARAM = "client_ip";//ip地址
    public static final String TIMESTAMP_PARAM = "timestamp";//时间戳
    public static final String WIDTH_PARAM = "width";//屏幕宽度
    public static final String HEIGHT_PARAM = "height";//
    public static final String MAC_ADDRESS_PARAM = "mac_Address";//
    public static final String IMEI_PARAM = "imei";//
    public static final String PARAM = "data";//参数数组
    public static final String PLATFORMTYPE_ANDROID = "android";

    /**
     * 后台接口
     */
    public static final String QUERYADVERT = "http://172.16.88.46:8080/gps-web" + "/m/v1/user/queryAdvert.json";

    /**
     * 获取图片
     */
    public static final String URL_IMAGE = "http://server.jeasonlzy.com/OkHttpUtils/" + "image";

    /**
     * 上传文件
     */
    public static final String URL_FORM_UPLOAD = "http://server.jeasonlzy.com/OkHttpUtils/" + "upload";

    /**
     * 上传文件
     */
    public static final String URL_DOWNLOAD = "http://server.jeasonlzy.com/OkHttpUtils/" + "download";

    /**
     * 同步请求
     */
    public static final String URL_JSONOBJECT = "http://server.jeasonlzy.com/OkHttpUtils/" + "jsonObject";
}
