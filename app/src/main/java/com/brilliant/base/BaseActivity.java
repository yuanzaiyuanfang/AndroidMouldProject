package com.brilliant.base;

import android.os.Bundle;
import android.view.View;

import com.basemodule.base.IBaseActivity;
import com.basemodule.base.IBaseModel;
import com.basemodule.base.IBasePresenter;
import com.basemodule.utils.NetWorkUtils;
import com.brilliant.widget.ErrorLayout;
import com.brilliant.widget.PopupDialog;

/**
 * add your personal code here
 * Created by conan on 2017/2/20.
 */

public abstract class BaseActivity<T extends IBasePresenter, E extends IBaseModel> extends IBaseActivity<T, E>
        implements PresentationLayerFunc {

    private PresentationLayerFuncHelper presentationLayerFuncHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presentationLayerFuncHelper = new PresentationLayerFuncHelper(this);
    }

    @Override
    public void showProgressDialog() {
        presentationLayerFuncHelper.showProgressDialog();
    }

    @Override
    public void showProgressDialog(String notice) {
        presentationLayerFuncHelper.showProgressDialog(notice);
    }

    @Override
    public void hideProgressDialog() {
        presentationLayerFuncHelper.hideProgressDialog();
    }


    /**
     *
     */
    public PopupDialog confirmDialog;

    /**
     *
     */
    public void showLoading() {
        startProgressDialog();
    }

    /**
     *
     */
    public void showLoading(String title) {
        startProgressDialog(title);
    }

    /**
     *
     */
    public void hideLoading() {
        stopProgressDialog();
    }

    /**
     *
     */
    public void showErrorTip(String msg) {
        showShortToast(msg);
    }

    /**
     * 刷新页面
     */
    @Override
    protected void updateViews(boolean isRefresh) {
    }

    /**
     * 检查网络
     */
    public void checkNetWork(ErrorLayout error_layout, ErrorLayout.CallClickListener listener) {
        if (!NetWorkUtils.isNetConnected(this)) {
            error_layout.setVisibility(View.VISIBLE);
            error_layout.viewListener(listener);
        } else {
            listener.callListener();
        }
    }
}
