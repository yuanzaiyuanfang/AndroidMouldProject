package com.brilliant;

import com.basemodule.widget.SimpleButton;
import com.brilliant.base.BaseActivity;
import com.brilliant.local.sharePref.EBSharedPrefVersion;
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

    //#################################################################### 自定义变量 start

    @BindView(R.id.sb_skip)
    SimpleButton mSbSkip;

    private boolean mIsSkip = false;

    //#################################################################### 自定义变量 end

    //#################################################################### 重写自定义方法 start

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
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

    //#################################################################### 重写自定义方法 end

    //#################################################################### 自定义方法 start

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
                            getString(EBSharedPrefVersion.PREFS_PREVERSION, "0.0.0"))) {
                UIFactory.startGuideActivity(this, RESULT_OK);
            } else {
                // 正常的页面流程
                // UIFactory.startHomeActivity(this, RESULT_OK);

                // 代码示例
                UIFactory.startOkGoTestActivity(this, RESULT_OK);
            }
            //===
            finish();
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    //#################################################################### 自定义方法 end

    //#################################################################### 重写系统方法 start

    @OnClick(R.id.sb_skip)
    public void onClick() {
        _doSkip();
    }

    @Override
    public void onBackPressed() {
        // 不响应后退键
        return;
    }

    //#################################################################### 重写系统方法 end
}
