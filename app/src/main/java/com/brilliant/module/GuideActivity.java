package com.brilliant.module;

import com.brilliant.AndroidAPP;
import com.brilliant.R;
import com.brilliant.base.BaseActivity;
import com.brilliant.local.sharePref.EBSharedPrefVersion;
import com.brilliant.utils.RxHelper;
import com.brilliant.utils.UIFactory;
import com.orhanobut.logger.Logger;

import rx.Subscriber;

/**
 * description:
 * Date: 2017/2/13 11:10
 * User: Administrator
 */
public class GuideActivity extends BaseActivity {

    //======================================= 自定义变量 start ======================================

    private boolean mIsSkip = false;

    //======================================= 自定义变量 end ========================================

    //======================================= 重写自定义方法 start ==================================

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_guide;
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
                    }
                });
    }

    //====================================== 重写自定义方法 end =====================================

    //====================================== 自定义方法 start =======================================

    /**
     *
     */
    private void _doSkip() {
        if (!mIsSkip) {
            mIsSkip = true;
            //在sp中记录访问过引导页的状态
            AndroidAPP.ebSharedPrefManager.getKDPreferenceUserInfo().saveString(EBSharedPrefVersion.PREFS_PREVERSION,
                    AndroidAPP.getPackageInfo().versionName);
            UIFactory.startHomeActivity(this, RESULT_OK);
            //===
            finish();
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    /**
     * @Description
     * @author BrillantZhao
     * @date 2015-1-16 下午12:56:28
     */
    private void backPressConform() {
        finish();
    }

    //====================================== 自定义方法 end =========================================

    //====================================== 重写系统方法 start =====================================

    @Override
    public void onBackPressed() {
        backPressConform();
    }

    //====================================== 重写系统方法 end =======================================
}
