package com.brilliant.injector.modules;

import android.content.Context;

import com.brilliant.AndroidAPP;
import com.brilliant.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/19.
 * Application Module
 */
@Module
public class ApplicationModule {

    private final AndroidAPP mApplication;
    private final RxBus mRxBus;

    public ApplicationModule(AndroidAPP application,  RxBus rxBus) {
        mApplication = application;
        mRxBus = rxBus;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return mRxBus;
    }
}
