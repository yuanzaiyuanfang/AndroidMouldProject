package com.brilliant.module.mvpmodle.news.newslist;

import com.basemodule.base.ILoadDataView;
import com.brilliant.adapter.item.NewsMultiItem;
import com.brilliant.module.mvpmodle.news.bean.NewsInfo;

import java.util.List;

/**
 * Created by long on 2016/8/23.
 * 新闻列表视图接口
 */
public interface INewsListView extends ILoadDataView<List<NewsMultiItem>> {

    /**
     * 加载广告数据
     * @param newsBean 新闻
     */
    void loadAdData(NewsInfo newsBean);
}
