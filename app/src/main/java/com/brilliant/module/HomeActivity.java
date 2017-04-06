package com.brilliant.module;

import android.os.SystemClock;

import com.basemodule.base.IBaseActivity;
import com.brilliant.R;

public class HomeActivity extends IBaseActivity {

    //======================================= 自定义变量 start ======================================

    private long mBackPressedTime; // 退出应用相关

    //======================================= 自定义变量 end ========================================

    //======================================= 重写自定义方法 start ==================================

    //====================================== 重写自定义方法 end =====================================

    //====================================== 自定义方法 start =======================================

    /**
     * 退出登录
     */
    private void userExit() {
        long curTime = SystemClock.uptimeMillis();
        if ((curTime - mBackPressedTime) < (2 * 1000)) {
            finish();
        } else {
            mBackPressedTime = curTime;
//            ToastUtils.showToast(R.string.app_exit_confirm);
        }
        finish();
    }

    //====================================== 自定义方法 end =========================================

    //====================================== 重写系统方法 start =====================================

    @Override
    public void onBackPressed() {
        userExit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    //====================================== 重写系统方法 end =======================================
}
