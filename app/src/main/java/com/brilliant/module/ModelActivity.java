package com.brilliant.module;

import com.brilliant.R;
import com.brilliant.base.BaseActivity;

/**
 * description:
 * Date: 2017/2/13 11:10
 * User: Administrator
 */
public class ModelActivity extends BaseActivity {

    //=================================== 自定义变量 start ==================================


    //=================================== 自定义变量 end ==================================

    //=================================== 重写自定义方法 start ==================================

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_model;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //=================================== 重写自定义方法 end ==================================

    //=================================== 自定义方法 start ==================================

    //=================================== 自定义方法 end ==================================

    //=================================== 重写系统方法 start ==================================

    @Override
    public void onBackPressed() {
        backPressConform();
    }

    /**
     * @Description
     * @author BrillantZhao
     * @date 2015-1-16 下午12:56:28
     */
    private void backPressConform() {
        finish();
    }

    //=================================== 重写系统方法 end ==================================
}
