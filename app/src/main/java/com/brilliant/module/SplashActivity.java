package com.brilliant.module;

import com.basemodule.widget.SimpleButton;
import com.brilliant.AndroidAPP;
import com.brilliant.R;
import com.brilliant.base.BaseActivity;
import com.brilliant.local.sharePref.EBSharedPrefUser;
import com.brilliant.utils.RxHelper;
import com.brilliant.utils.UIFactory;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;


/**
 * 启动页面
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.sb_skip)
    SimpleButton mSbSkip;

    private boolean mIsSkip = false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        RxHelper.countdown(3)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        _doSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i("onError-->" + e.getMessage());
                        _doSkip();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mSbSkip.setText("跳过 " + integer);
                    }
                });
    }

    /**
     *
     */
    private void _doSkip() {
        if (!mIsSkip) {
            mIsSkip = true;
            //跳转到MainActivity，并结束当前的LauncherActivity
            //=== 和本次的软件版本号作对比，用来判断软件是否进行了更新，从而决定是否展示引导页
            if (AndroidAPP.getPackageInfo() != null && !AndroidAPP.getPackageInfo().versionName.equals(
                    AndroidAPP.ebSharedPrefManager.
                            getKDPreferenceUserInfo().
                            getString(EBSharedPrefUser.PREFS_PREVERSION, "0.0.0"))) {
                UIFactory.startGuideActivity(this, RESULT_OK);
            } else {
                UIFactory.startHomeActivity(this, RESULT_OK);
            }
            //===
            finish();
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    @OnClick(R.id.sb_skip)
    public void onClick() {
        _doSkip();
    }

    @Override
    public void onBackPressed() {
        // 不响应后退键
        return;
    }
}
