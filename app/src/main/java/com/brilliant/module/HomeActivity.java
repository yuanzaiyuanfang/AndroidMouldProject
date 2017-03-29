package com.brilliant.module;

import android.os.SystemClock;

import com.blankj.utilcode.utils.ToastUtils;
import com.brilliant.R;
import com.brilliant.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    private long mBackPressedTime; // 退出应用相关

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

    //==============================================================================================

    @Override
    public void onBackPressed() {
        userExit();
    }

    /**
     * 退出登录
     */
    private void userExit() {
        long curTime = SystemClock.uptimeMillis();
        if ((curTime - mBackPressedTime) < (2 * 1000)) {
            finish();
        } else {
            mBackPressedTime = curTime;
            ToastUtils.showToast(R.string.app_exit_confirm);
        }
        finish();
    }
}
