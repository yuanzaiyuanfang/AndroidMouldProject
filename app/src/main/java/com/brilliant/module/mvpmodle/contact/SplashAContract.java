package com.brilliant.module.mvpmodle.contact;

import com.basemodule.base.IBaseModel;
import com.basemodule.base.IBasePresenter;
import com.basemodule.base.IBaseView;
import com.brilliant.module.mvpmodle.bean.QueryAdvertBean;

import rx.Observable;

/**
 * Created by Conan on 2017/2/23.
 */
public interface SplashAContract {

    abstract class Presenter extends IBasePresenter<View, Model> {

        public abstract void initZip();

        public abstract void queryAdvert();

        public abstract void jump2Main(boolean hasAd);

        public abstract void removeTimer();
    }

    interface Model extends IBaseModel {

        Observable<String> initZip();

        Observable<QueryAdvertBean.DataBean> queryAdvert();
    }

    interface View extends IBaseView {
        void returnInitZip(String url);

        void returnQueryAdvert(QueryAdvertBean.DataBean bean);

        void returnJump2Main();

    }

}
