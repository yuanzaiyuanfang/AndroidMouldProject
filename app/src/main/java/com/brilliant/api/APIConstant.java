package com.brilliant.api;

/**
 * Created by conan on 2017/2/18.
 */

public class APIConstant {

    public static boolean isDebug = true;
    public static boolean isAsset = false;//H5 wx2包存放位置
    public static final String SERVICE_URL = isDebug ? "http://172.16.88.46:8080/gps-web" : "https://www.lewei666.com";
    public static final String SERVICE_DEMAIN = isDebug ? ".lewei.com" : ".lewei666.com";//cookie相关 具体未知

    /**
     * important key & id
     */
    public final static String accountID = "151233";// alicloud
    public final static String appId = "88248-1";// hotfix appid

    /**
     * Cache File Path Related
     */
    public static final String CACHE_FILE = "/lewei";
    public static final String CACHE_IMG = "/image";
    public static final String UpdateApplication = "/updateApplication";//保存升级APK的目录

    /**
     * LOGCAT TAG
     */
    public final static String TAG_HTTPDNS = "HttpDNSInterceptor";
    public final static String TAG_HOTFIX = "HotFixRelated";
    public final static String TAG_GETUIINTENTSERVICE = "GeTuiIntentService";
    public final static String TAG_CHECKAPPUPDATEINFO = "CheckAppUpdateInfo";
    public final static String TAG_BINDCLIENT = "bindClient";
    public final static String TAG_REQUESTPERMISSIONSRESULT = "RequestPermissions";//授权相关
    public final static String TAG_DOWNLOADAPK = "DownLoadAPK";//APK下载
    public final static String TAG_BAIDUMAP = "BAIDUMAP_RELATED";//地图其他
    public final static String TAG_BDLOCATION = "BDLocation_related";//定位相关
    public final static String TAG_RXSUBSCRIBER = "RxSubscriber_ONERROR";//请求失败或解析失败_依赖包手写

    /**
     * Local SharePreference stored key
     */
    public static final String KEY_SESSION_TOKEN = "sessionToken";//session_token
    public static final String KEY_PHONENUM = "phoneNum";//未知
    public static final String KEY_PRE_ZIPCODE = "zipCode";
    public static final String KEY_LOGINED = "logined";
    public static final String KEY_EQU_SEARCH_HISTORY ="equmgr_search_histroy";
    public static final String FIRSTLOAD = "map_whether_teach";//首次打开地图功能需要显示教学蒙板
    public static final String KEY_LOAN_SEARCH_HISTORY ="loanmgr_search_histroy";
    public static final String KEY_MSGALARM_SEARCH_HISTROY ="msgalarm_search_histroy";
    public static final String KEY_SPLASH_ADVERTISEMENT ="SPLASH_ADVERTISEMENT";

    /**
     * For all Intent
     */
    public static final String NAME_TITLECOLOR = "titleColor";
    public static final String NAME_BACKHIDE = "backHide";
    public static final String NAME_URL = "url";
    public static final String NAME_ISJUMP = "isJump";
    public static final String NAME_RESULT = "result";
    public static final String NAME_TITLE = "title";
    public static final String NAME_TIP = "tip";
    public static final String NAME_TOKEN = "token";
    public static final String NAME_ISLOAD = "isLoad";
    public static final String NAME_DOWNURL = "downUrl";
    public static final String NAME_ZIPCODE = "zipCode";
    public static final String NAME_CLEARCACHE = "clearcache";
    public static final String NAME_KEY_APP_NAME = "Key_App_Name";
    public static final String NAME_KEY_DOWN_URL = "Key_Down_Url";
    public static final String NAME_IMEI_LIST = "imei_list";
    public static final String NAME_EXIT = "ifquit";
    public static final String NAME_EQPTBEAN = "eqptbean";
    public static final String NAME_IMEI_BEAN_LIST = "imei_bean_list";
    public static final String NAME_CORPBEAN = "corpbean";
    public static final String NAME_GROUPINFO = "groupInfo";
    public static final String NAME_IMEI = "imei";
    public static final String NAME_CARID = "carid";
    public static final String NAME_PHONENUM = "phoneNum";
    public static final String NAME_SMSTYPE = "smstype";
    public static final String NAME_EQSBEAN_LIST = "eqsBean_list";
    public static final String NAME_INSTRUCTIONOPT = "instructionOpt";
    public static final String NAME_STYLE_TYPE = "style_type";
    public static final String NAME_LICENSENUM = "licenseNum";
    public static final String NAME_ALARMBEAN = "alarmbean";
    public static final String NAME_ID = "id";
    public static final String NAME_DISPOSEOPTION = "disposeOption";
    public static final String NAME_REMARK = "remark";
    public static final String NAME_STATUS = "status";

    public static final int TAG_INIT = 999;
    public static final int TAG_DOWN_ZIP_OK = 998;
    public static final int TAG_DOWN_ZIP_ERROR = 997;
    public static final int TAG_DOWN_ZIP_START = 1000;
    public static final int TAG_DOWN_ZIP_PROGRESS = 1001;//下载进度
    public static final int IMAGE_FORCARMER = 101;
    public static final int IMAGE_FORCARMER_H5 = 102;
    public static final int IMAGE_FORIMAGES = 103;
    public static final int PHOTO_PICKED_WITH_DATA = 104;
    public static final int SHOW_IMEI_LIST = 799;
    public static final int MOVE_EQPTS_MOVEHANDLE = 798;
    public static final int EQUMOVEHANDLES1ACTIVITY_MOVEEQU = 979;
    public static final int GOT_CARIDS = 996;
    public static final int NORMALJUMP = 111;// startactivity
    public static final int SMS_CONFIRM = 899;
    public static final int HANDLE_MSGALAM = 995;

    /**
     * BroadCastReceiver Action
     */
    public static final String RECEIVER_ERROR_RELOGIN_MAINACTIVITY = "com.rjs.lewei.RECEIVER_ERROR_RELOGIN";

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
    public static final String CHECKAPPUPDATE = SERVICE_URL + "/m/common/checkAppUpdate.json";
    public static final String CHECKH5UPDATE = SERVICE_URL + "/m/common/checkH5Update.json";
    public static final String QUERYCARBYNUMNAME = SERVICE_URL + "/m/v1/monitor/querycarbynumname.json";
    public static final String QUERYCARINFOWITHEQU = SERVICE_URL + "/m/v1/monitor/querycarinfowithequ.json";
    public static final String QUERYCARLOCATION = SERVICE_URL + "/m/v2/monitor/querycarlocation.json";
    public static final String QUERYCARINFO = SERVICE_URL + "/m/v1/monitor/querycarinfo.json";
    public static final String CARSCAN = SERVICE_URL + "/m/v1/car/scan.json";
    public static final String BINDCLIENT = SERVICE_URL + "/m/v1/user/bindclient.json";
    public static final String UNBINDCLIENT = SERVICE_URL + "/m/v1/user/unbindclient.json";
    public static final String QUERYGROUPMONTH = SERVICE_URL + "/m/v1/monitor/querygroupmonth.json";
    public static final String SETPUBLICGROUP = SERVICE_URL + "/m/v1/monitor/setPublicGroup.json";
    public static final String QUERYCORPCAR = SERVICE_URL + "/m/v1/monitor/querycorpcar.json";
    public static final String QUERYATTENTCARLIST = SERVICE_URL + "/m/v2/monitor/queryAttentCarList.json";
    public static final String SENDWIFISMSCODE = SERVICE_URL + "/m/v1/monitor/sendWifiSmsCode.json";
    public static final String CHECKUSERSMSCODE = SERVICE_URL + "/m/v1/monitor/checkUserSmsCode.json";
    public static final String QUERYINSTRUCTION = SERVICE_URL + "/m/v1/equ/queryInstruction.json";
    public static final String SETINSTRUCTION = SERVICE_URL + "/m/v1/monitor/setInstruction.json";
    public static final String QUERYATTENT = SERVICE_URL + "/m/v1/monitor/queryAttent.json";
    public static final String SETATTENT = SERVICE_URL + "/m/v1/monitor/setAttent.json";
    public static final String QUERYPER = SERVICE_URL + "/m/v1/alarm/queryPer.json";
    public static final String SETPER = SERVICE_URL + "/m/v1/alarm/setPer.json";
    public static final String SETTINGROLE = SERVICE_URL + "/m/v1/monitor/settingRole.json";
    public static final String QUERYALARMTYPE = SERVICE_URL + "/m/v1/alarm/queryAlarmType.json";
    public static final String QUERYGROUP = SERVICE_URL + "/m/v1/monitor/querygroup.json";
    public static final String QUERYALARMLIST = SERVICE_URL + "/m/v1/alarm/queryAlarmList.json";
    public static final String HANDLE = SERVICE_URL + "/m/v1/alarm/handle.json";
    public static final String COUNT = SERVICE_URL + "/m/v1/playback/count.json";
    public static final String DAYPOINTS = SERVICE_URL + "/m/v1/playback/daypoints.json";
    public static final String UNHANDLEALARM = SERVICE_URL + "/m/v1/message/unhandlealarm.json";
    public static final String UNREAD = SERVICE_URL + "/m/v1/message/unread.json";
    public static final String QUERYLIST = SERVICE_URL + "/m/v1/message/querylist.json";
    public static final String RETRYLOGIN = SERVICE_URL + "/m/v1/user/retrylogin.json";
    public static final String READ = SERVICE_URL + "/m/v1/message/read.json";
    public static final String SENDOILSMSCODE = SERVICE_URL + "/m/v1/monitor/sendOilSmsCode.json";
    public static final String CHECKOILSMSCODE = SERVICE_URL + "/m/v1/monitor/checkOilSmsCode.json";
    public static final String QUERYDEFAULTEQPT = SERVICE_URL + "/m/v1/equ/queryDefaultEqpt.json";
    public static final String SETDEFAULTEQPT = SERVICE_URL + "/m/v1/equ/setDefaultEqpt.json";
    public static final String SETONOFFOIL = SERVICE_URL + "/m/v1/monitor/setOnOffOil.json";
    public static final String SETONOFFDEFENSE = SERVICE_URL + "/m/v1/equ/setOnOffDefense.json";
    public static final String QUERYOVERSPEED = SERVICE_URL + "/m/v1/monitor/queryOverSpeed.json";
    public static final String SAVEOVERSPEED = SERVICE_URL + "/m/v1/monitor/saveOverSpeed.json";
    public static final String SUGGEST = SERVICE_URL + "/m/v1/about/suggest.json";
    public static final String QUERYLOANLIST = SERVICE_URL + "/m/v1/loan/queryLoanList.json";
    public static final String QUERYPUSHINFO = SERVICE_URL + "/m/v1/message/queryPushInfo.json";
    public static final String SETPUSHMSG = SERVICE_URL + "/m/v1/message/setPushMsg.json";
    public static final String QUERYEQPTLIST = SERVICE_URL + "/m/v1/eqpt/queryEqptList.json";
    public static final String MOVEEQPT = SERVICE_URL + "/m/v1/eqpt/moveEqpt.json";
    public static final String ASSEMBLE = SERVICE_URL + "/m/v1/equ/assemble.json";
    public static final String DISASSEMBLE = SERVICE_URL + "/m/v1/eqpt/disassemble.json";
    public static final String CHECKIMEIISEXIST = SERVICE_URL + "/m/v1/eqpt/checkImeiIsExist.json";
    public static final String ASSET = SERVICE_URL + "/m/v1/eqpt/asset.json";
    public static final String QUERYCORP = SERVICE_URL + "/m/v2/eqpt/queryCorp.json";
    public static final String QUERYGROUPV2 = SERVICE_URL + "/m/v2/eqpt/queryGroup.json";
    public static final String MOVEEQPTV2 = SERVICE_URL + "/m/v2/eqpt/moveEqpt.json";
    public static final String ASSETV2 = SERVICE_URL + "/m/v2/eqpt/asset.json";
    public static final String QUERYPOSITION = SERVICE_URL + "/m/v1/equ/queryPosition.json";
    public static final String UPLOADIMAGEBASE64 = SERVICE_URL + "/m/v2/equ/uploadImageBase64.json";
    public static final String UPDATEPOSITION = SERVICE_URL + "/m/v2/equ/updatePosition.json";
    public static final String QUERYADVERT = SERVICE_URL + "/m/v1/user/queryAdvert.json";
}
