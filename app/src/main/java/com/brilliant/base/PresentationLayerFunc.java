package com.brilliant.base;

/**
 * <页面基础公共功能抽象>
 *
 * @author caoyinfei
 * @version [版本号, 2016/6/6]
 * @see [相关类/方法]
 * @since [V1]
 */
public interface PresentationLayerFunc {

    /**
     * 网络请求加载框
     */
    void showProgressDialog();

    /**
     * 网络请求加载框
     */
    void showProgressDialog(String notice);

    /**
     * 隐藏网络请求加载框
     */
    void hideProgressDialog();

}
