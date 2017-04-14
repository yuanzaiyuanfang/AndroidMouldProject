package com.brilliant.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basemodule.base.IBaseFragment;
import com.basemodule.base.IBaseModel;
import com.basemodule.base.IBasePresenter;

/**
 * add your personal code here
 * description:
 * Date: 2017/4/6 16:38
 * User: Administrator
 */
public abstract class BaseFragment<T extends IBasePresenter, E extends IBaseModel> extends IBaseFragment<T, E>
        implements PresentationLayerFunc {

    private PresentationLayerFuncHelper presentationLayerFuncHelper;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        presentationLayerFuncHelper = new PresentationLayerFuncHelper(getActivity());
        return super.rootView;
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
}
