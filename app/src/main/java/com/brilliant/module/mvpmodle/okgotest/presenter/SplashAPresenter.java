package com.brilliant.module.mvpmodle.okgotest.presenter;

/**
 * Created by Conan on 2017/2/23.
 */


import com.basemodule.baserx.RxSubscriber;
import com.brilliant.module.mvpmodle.okgotest.bean.QueryAdvertBean;
import com.brilliant.module.mvpmodle.okgotest.contact.SplashAContract;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/****************
 * 使用例子
 ****************/
//mRxManage.add(mModel.demoMethod(params...).subscribe(new RxSubscriber<Object>(mContext, true) {method...}));
public class SplashAPresenter extends SplashAContract.Presenter {

    private Subscription mob;

    @Override
    public void initZip() {
        mRxManage.add(mModel.initZip().subscribe(new RxSubscriber<String>(mContext, false) {
            @Override
            protected void _onNext(String url) {
                mView.returnInitZip(url);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void queryAdvert() {
        mRxManage.add(mModel.queryAdvert().subscribe(new RxSubscriber<QueryAdvertBean.DataBean>(mContext, false) {
            @Override
            protected void _onNext(QueryAdvertBean.DataBean bean) {
                mView.returnQueryAdvert(bean);
            }

            @Override
            protected void _onError(String message) {
            }
        }));
    }

    @Override
    public void jump2Main(boolean hasAd) {
        if (!hasAd) {
            mView.returnJump2Main();
            return;
        }
        mRxManage.add(mob = Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mView.returnJump2Main();
                    }
                }));
    }

    @Override
    public void removeTimer() {
        // 取消跳转主页倒计时
        mob.unsubscribe();
    }
}
