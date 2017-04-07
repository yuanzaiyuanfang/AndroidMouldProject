package com.brilliant.module.mvpmodle.okgotest.model;

import android.content.Context;

import com.basemodule.baserx.RxSchedulers;
import com.basemodule.local.sharedpref.SharedPrefUtils;
import com.brilliant.api.APIMethod;
import com.brilliant.api.APIConstant;
import com.brilliant.module.mvpmodle.okgotest.bean.QueryAdvertBean;
import com.brilliant.module.mvpmodle.okgotest.contact.SplashAContract;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Conan on 2017/2/23.
 */

/****************
 * 使用例子
 ****************/
//  APIMethod.getInstance().errorObservable("失败");
//  APIMethod.getInstance().demoMethod(mContext, params...).flatMap(Func...).map(Func...).compose(RxSchedulers.<Object>io_main());

public class SplashAModel implements SplashAContract.Model {

    Context mContext;

    @Override
    public void setTag(Context context) {
        mContext = context;
    }

    @Override
    public Observable<String> initZip() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
//                InputStream myInput = null;
//                try {
//                    myInput = mContext.getAssets().open("wx2.zip");
//                    ZIP.UnZipFolder(myInput, mContext.getFilesDir().getAbsolutePath());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                String url = StoredData.IsLogin() ? "index.html" : "view/user/login.html";
//                subscriber.onNext(url);
//                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<String>io_main());
    }

    @Override
    public Observable<QueryAdvertBean.DataBean> queryAdvert() {
        return APIMethod.getInstance().queryAdvert(mContext)
                .map(new Func1<QueryAdvertBean, QueryAdvertBean.DataBean>() {
                    @Override
                    public QueryAdvertBean.DataBean call(QueryAdvertBean queryAdvertBean) {

                        QueryAdvertBean.DataBean bean_new = queryAdvertBean.getData();

                        QueryAdvertBean.DataBean bean_old = SharedPrefUtils.getSharedObject(APIConstant.KEY_SPLASH_ADVERTISEMENT, null);

                        if (bean_old == null || !bean_old.getKey().equals(bean_new.getKey())) {

                            //首次获取广告信息, 初始化可显示次数，并保存
                            bean_new.setLeftShowNum(bean_new.getShowNum() - 1);

                        } else if (bean_old.getKey().equals(bean_new.getKey())) {

                            //再次获取到相同的广告信息,更新该广告的可显示次数和默认信息
                            if (bean_old.getLeftShowNum() > bean_new.getShowNum()) {
                                bean_new.setLeftShowNum(bean_new.getShowNum() - 1);
                            } else {
                                bean_new.setLeftShowNum(bean_old.getLeftShowNum() - 1);
                            }

                        }

                        SharedPrefUtils.setSharedObject(APIConstant.KEY_SPLASH_ADVERTISEMENT, bean_new);

                        return bean_new;
                    }
                }).compose(RxSchedulers.<QueryAdvertBean.DataBean>io_main());
    }
}
