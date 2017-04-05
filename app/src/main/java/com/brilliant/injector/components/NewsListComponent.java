package com.brilliant.injector.components;

import com.brilliant.injector.PerFragment;
import com.brilliant.injector.modules.NewsListModule;
import com.brilliant.module.mvpmodle.news.newslist.NewsListFragment;

import dagger.Component;

/**
 * Created by long on 2016/8/23.
 * 新闻列表 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = NewsListModule.class)
public interface NewsListComponent {
    void inject(NewsListFragment fragment);
}
