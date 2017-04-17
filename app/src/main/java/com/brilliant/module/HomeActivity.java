package com.brilliant.module;

import android.os.SystemClock;

import com.blankj.utilcode.util.ToastUtils;
import com.brilliant.R;
import com.brilliant.base.BaseActivity;
import com.brilliant.constant.UIFactory;

public class HomeActivity extends BaseActivity {

    //#################################################################### 自定义变量 start

    private long mBackPressedTime; // 退出应用相关

    private String download_url = "https://172.16.88.230:8383/apk/ced9ab08-3d7d-4cc7-882d-2d81f72a0976.apk";

    //#################################################################### 自定义变量 end

    //#################################################################### 重写自定义方法 start

    //#################################################################### 重写自定义方法 end

    //#################################################################### 自定义方法 start

    /**
     * 退出登录
     */
    private void userExit() {
        long curTime = SystemClock.uptimeMillis();
        if ((curTime - mBackPressedTime) < (2 * 1000)) {
            finish();
        } else {
            mBackPressedTime = curTime;
            ToastUtils.showShortToast(R.string.app_exit_confirm);
        }
        finish();
    }

    /**
     * 开启升级
     */
    private void downloadUpdateAPK(String download_url) {
        UIFactory.openDownLoadService(mContext, download_url, "中...");
    }

    //#################################################################### 自定义方法 end

    //#################################################################### 重写系统方法 start

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
        downloadUpdateAPK(download_url);
    }

    @Override
    protected void updateViews(boolean b) {

    }

    //#################################################################### 重写系统方法 end
}
