package com.brilliant.base;

import android.os.Bundle;

import com.basemodule.base.IBaseActivity;
import com.basemodule.base.IBaseModel;
import com.basemodule.base.IBasePresenter;
import com.blankj.utilcode.util.ToastUtils;

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

    @Override
    public void showLoading() {
        if (!isFinishing()) {
            try {
                showProgressDialog();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void showErrorTip(String s) {
        ToastUtils.showShortToast(s);
    }
}
