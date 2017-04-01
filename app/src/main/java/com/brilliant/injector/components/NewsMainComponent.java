package com.brilliant.injector.components;

import com.brilliant.injector.PerFragment;
import com.brilliant.injector.modules.NewsMainModule;
import com.brilliant.module.mvpmodle.news.main.NewsMainFragment;

import dagger.Component;

/**
 * Created by long on 2016/12/20.
 * 主页 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = NewsMainModule.class)
public interface NewsMainComponent {
    void inject(NewsMainFragment fragment);
}
