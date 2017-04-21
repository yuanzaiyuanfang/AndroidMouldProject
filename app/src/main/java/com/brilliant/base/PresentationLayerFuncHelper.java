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

    private CustomProgressDialog progressDialog;

    public PresentationLayerFuncHelper(Context context) {
        this.context = context;
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);
        }
        progressDialog.show();
    }

    @Override
    public void showProgressDialog(String notice) {
        if (progressDialog == null) {
            progressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);
        }
        progressDialog.showNotice(notice);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
