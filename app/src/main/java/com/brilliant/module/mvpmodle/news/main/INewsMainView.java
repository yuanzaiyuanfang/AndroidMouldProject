package com.brilliant.module.mvpmodle.news.main;


import com.brilliant.local.table.NewsTypeInfo;

import java.util.List;

/**
 * Created by long on 2016/9/1.
 * 主页接口
 */
public interface INewsMainView {

    /**
     * 显示数据
     * @param checkList     选中栏目
     */
    void loadData(List<NewsTypeInfo> checkList);
}
