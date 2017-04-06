package com.brilliant.module.mvpmodle.okgotest.contact;

import com.brilliant.base.BaseModel;
import com.brilliant.base.BasePresenter;
import com.brilliant.base.BaseView;
import com.brilliant.module.mvpmodle.okgotest.bean.QueryAdvertBean;

import rx.Observable;

/**
 * Created by Conan on 2017/2/23.
 */
public interface SplashAContract {

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void initZip();

        public abstract void queryAdvert();

        public abstract void jump2Main(boolean hasAd);

        public abstract void removeTimer();
    }

    interface Model extends BaseModel {

        Observable<String> initZip();

        Observable<QueryAdvertBean.DataBean> queryAdvert();
    }

    interface View extends BaseView {
        void returnInitZip(String url);

        void returnQueryAdvert(QueryAdvertBean.DataBean bean);

        void returnJump2Main();

    }

}
