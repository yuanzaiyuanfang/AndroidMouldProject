package com.brilliant.demo;


import com.brilliant.base.BaseModel;
import com.brilliant.base.BasePresenter;
import com.brilliant.base.BaseView;

/**
 * MVP中Contract 模板
 * Created by Conan on 2017/2/23.
 */
public interface DemoContract {

    abstract class Presenter extends BasePresenter<View, Model> {
        //        public abstract void demoMethod (Object params...);
    }

    interface Model extends BaseModel {
        //        Observable<String> demoMethod(Object params...);

    }

    interface View extends BaseView {
        //        void returnDemoMethod(Object params...);
    }

}
