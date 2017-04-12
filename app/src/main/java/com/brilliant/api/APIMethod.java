package com.brilliant.api;

import android.content.Context;

import com.basemodule.baserx.RxSchedulers;
import com.basemodule.okgo.OkGoRequest;
import com.brilliant.module.mvpmodle.okgotest.bean.QueryAdvertBean;
import com.lzy.okrx.RxAdapter;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by conan on 2017/2/18.
 */

public class APIMethod {

    public static volatile APIMethod api;

    private APIMethod() {
    }

    /**
     * @return
     */
    public static APIMethod getInstance() {
        if (api == null) {
            api = new APIMethod();
        }
        return api;
    }

    //  手写错误抛出
    public Observable<String> errorObservable(final String errorMsg) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                throw new IllegalStateException(errorMsg);
            }
        }).compose(RxSchedulers.<String>io_main());
    }

    /**
     * 添加网络请求基本参数
     *
     * @param params
     * @return
     */
    public HashMap<String, Object> addBaseParams(HashMap<String, Object> params) {
        params.put(APIConstant.PLATFORMTYPE_PARAM,
                APIConstant.PLATFORMTYPE_ANDROID);
        params.put(APIConstant.APP_CHANNEL_PARAM, null);
        params.put(APIConstant.APP_SIGN__PARAM, null);
        params.put(APIConstant.CLIENT_IP_PARAM, null);
        params.put(APIConstant.IMEI_PARAM, null);
        params.put(APIConstant.MAC_ADDRESS_PARAM, null);
        params.put(APIConstant.TIMESTAMP_PARAM, System.currentTimeMillis());
        return params;
    }

    /**
     *
     * @param params
     * @return
     */
    public HashMap<String, Object> getPublicParams(HashMap<String, Object> params) {
        //=== 公共部分设置
        HashMap<String, Object> map = addBaseParams(new HashMap<String, Object>());
        map.put(APIConstant.SESSION_TOKEN_PARAM, "token");
        map.put(APIConstant.PARAM, params);
        //===
        return map;
    }

    //#################################################################### 具体页面接口

    // 闪页 获取广告信息
    public Observable<QueryAdvertBean> queryAdvert(Context context) {
        //=== 参数设置
        HashMap<String, Object> params = new HashMap<>();

        //===
        return OkGoRequest.postJsonRequest(context, APIConstant.QUERYADVERT, getPublicParams(params)).getCall(new JsonConvert<QueryAdvertBean>() {
        }, RxAdapter.<QueryAdvertBean>create());
    }
}
