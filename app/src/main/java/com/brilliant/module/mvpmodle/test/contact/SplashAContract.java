package com.brilliant.module.mvpmodle.test.contact;

import android.graphics.Bitmap;

import com.brilliant.base.BaseModel;
import com.brilliant.base.BasePresenter;
import com.brilliant.base.BaseView;
import com.brilliant.module.mvpmodle.test.bean.QueryAdvertBean;

import java.io.File;
import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Conan on 2017/2/23.
 */
public interface SplashAContract {

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void queryAdvert();

        public abstract void getBitmap();

        public abstract void uploadFile(ArrayList<File> files);

        public abstract void downloadFile();

    }

    interface Model extends BaseModel {

        Observable<QueryAdvertBean.DataBean> queryAdvert();

        Observable<Bitmap> getBitmap();

        Observable<String> uploadFile(ArrayList<File> files);

        Observable<File> downloadFile();
    }

    interface View extends BaseView {

        void returnQueryAdvert(QueryAdvertBean.DataBean bean);

        void returnBitmap(Bitmap bitmap);

        void returnUploadFile(String string);

        void returnDownloadFile(File file);

    }

}
