package com.brilliant.injector.modules;

import com.basemodule.base.IBasePresenter;
import com.brilliant.adapter.NewsMultiListAdapter;
import com.brilliant.injector.PerFragment;
import com.brilliant.module.mvpmodle.news.newslist.NewsListFragment;
import com.brilliant.module.mvpmodle.news.newslist.NewsListPresenter;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/23.
 * 新闻列表 Module
 */
@Module
public class NewsListModule {

    private final NewsListFragment mNewsListView;
    private final String mNewsId;

    public NewsListModule(NewsListFragment view, String newsId) {
        this.mNewsListView = view;
        this.mNewsId = newsId;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsListPresenter(mNewsListView, mNewsId);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new NewsMultiListAdapter(mNewsListView.getContext());
    }
}
