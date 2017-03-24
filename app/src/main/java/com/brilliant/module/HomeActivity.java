package com.brilliant.module;

import com.blankj.utilcode.utils.ToastUtils;
import com.brilliant.R;
import com.brilliant.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    private long mExitTime = 0;  // 检测退出

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
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

    @Override
    public void onBackPressed() {
        _exit();
    }

    /**
     * 退出
     */
    private void _exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtils.showToast("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
