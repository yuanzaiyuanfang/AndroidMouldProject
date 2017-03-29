package com.brilliant.injector.modules;

import com.basemodule.base.IRxBusPresenter;
import com.brilliant.adapter.ViewPagerAdapter;
import com.brilliant.injector.PerFragment;
import com.brilliant.local.table.DaoSession;
import com.brilliant.module.mvpmodle.news.main.NewsMainFragment;
import com.brilliant.module.mvpmodle.news.main.NewsMainPresenter;
import com.brilliant.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/12/20.
 * 新闻主页 Module
 */
@Module
public class NewsMainModule {

    private final NewsMainFragment mView;

    public NewsMainModule(NewsMainFragment view) {
        mView = view;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter provideMainPresenter(DaoSession daoSession, RxBus rxBus) {
        return new NewsMainPresenter(mView, daoSession.getNewsTypeInfoDao(), rxBus);
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }
}
