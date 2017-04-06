package com.brilliant.api;

import android.content.Context;

import com.basemodule.baserx.RxSchedulers;
import com.brilliant.module.mvpmodle.okgotest.bean.QueryAdvertBean;
import com.brilliant.utils.JsonConvert;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okrx.RxAdapter;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by conan on 2017/2/18.
 */

public class API {

    public static volatile API api;

    private API() {
    }

    /**
     * @return
     */
    public static API getInstance() {
        if (api == null) {
            api = new API();
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
     * 一般POST请求
     *
     * @param context
     * @param url
     * @param params
     * @return
     */
    private PostRequest getPostRequest(Context context, String url, HashMap<String, Object> params) {
        HashMap<String, Object> map = addBaseParams(new HashMap<String, Object>());
        map.put(APIConstant.SESSION_TOKEN_PARAM, "token");
        map.put(APIConstant.PARAM, params);
        return OkGo.post(url)
                .tag(context)//以对应activity或fragment作为网络请求tag，以便即时取消网络请求
                .upJson(new Gson().toJson(map));
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

    //#################################################################### 具体页面接口

    // 闪页 获取广告信息
    public Observable<QueryAdvertBean> queryAdvert(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        return getPostRequest(context, APIConstant.QUERYADVERT, params).getCall(new JsonConvert<QueryAdvertBean>() {
        }, RxAdapter.<QueryAdvertBean>create());
    }
}
