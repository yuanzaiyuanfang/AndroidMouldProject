package com.brilliant.base;

import com.basemodule.base.IBaseModel;
import com.basemodule.base.IBasePresenter;
import com.basemodule.base.IBaseView;

/**
 * description:
 * Date: 2017/4/6 16:39
 * User: Administrator
 */
public abstract class BasePresenter<T extends IBaseView, E extends IBaseModel> extends IBasePresenter<T, E> {
}