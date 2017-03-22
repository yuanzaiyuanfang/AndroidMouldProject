package com.brilliant.module;

import android.content.Intent;

import com.basemodule.widget.SimpleButton;
import com.brilliant.R;
import com.brilliant.base.BaseActivity;
import com.brilliant.utils.RxHelper;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * 开机界面由守护神镇着，图是我盗用的别人的，我想买但看到团购都过期了〒_〒
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
        RxHelper.countdown(5)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        _doSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        _doSkip();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mSbSkip.setText("跳过 " + integer);
                    }
                });
    }

    private void _doSkip() {
        if (!mIsSkip) {
            mIsSkip = true;
            finish();
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
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
