package com.brilliant.base;

import android.content.Context;

import com.brilliant.R;
import com.brilliant.widget.dialog.CustomProgressDialog;


/**
 * <页面基础公共功能实现>
 *
 * @author caoyinfei
 * @version [版本号, 2016/6/6]
 * @see [相关类/方法]
 * @since [V1]
 */
public class PresentationLayerFuncHelper implements PresentationLayerFunc {

    private Context context;

    private CustomProgressDialog loadingLayout;

    public PresentationLayerFuncHelper(Context context) {
        this.context = context;
    }

    @Override
    public void showProgressDialog() {
        if (loadingLayout == null) {
            loadingLayout = new CustomProgressDialog(context, R.style.CustomProgressDialog);
        }
        loadingLayout.show();
    }

    @Override
    public void showProgressDialog(String notice) {
        if (loadingLayout == null) {
            loadingLayout = new CustomProgressDialog(context, R.style.CustomProgressDialog);
        }
        loadingLayout.showNotice(notice);
        loadingLayout.show();
    }

    @Override
    public void hideProgressDialog() {
        if (loadingLayout != null) {
            loadingLayout.dismiss();
        }
    }
}
