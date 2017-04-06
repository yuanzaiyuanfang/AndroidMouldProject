package com.brilliant.module.mvpmodle.okgo;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.basemodule.base.IBaseActivity;
import com.basemodule.widget.EmptyLayout;
import com.brilliant.R;
import com.brilliant.module.mvpmodle.bean.QueryAdvertBean;
import com.brilliant.module.mvpmodle.contact.SplashAContract;
import com.brilliant.module.mvpmodle.model.SplashAModel;
import com.brilliant.module.mvpmodle.presenter.SplashAPresenter;
import com.trello.rxlifecycle.LifecycleTransformer;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * description:
 * Date: 2017/4/6 10:50
 * User: Administrator
 */
public class OkGoTestActivity extends IBaseActivity<SplashAPresenter, SplashAModel> implements SplashAContract.View{

    private static final String TAG = "OkGoTestActivity";

    @Bind(R.id.iv_splash)
    ImageView ivSplash;

    @Bind(R.id.iv_splash_r)
    ImageView ivSplashR;

    @Bind(R.id.splash_view)
    RelativeLayout splashView;

    @Bind(R.id.iv_adv)
    ImageView ivAdv;

    @Bind(R.id.tv_skip)
    TextView tvSkip;

    @Bind(R.id.rel_adv)
    RelativeLayout relAdv;

    private String startUrl = "";

    private boolean hasAdv = false;

    private String advUrl = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {

        SetTranslanteBar();

        //===
        mPresenter.queryAdvert();        //异步获取广告信息
    }

    @OnClick({R.id.tv_skip, R.id.tv_enteradv})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_skip: //跳过广告，直接进入主页
                mPresenter.jump2Main(false);
                break;

            case R.id.tv_enteradv: //跳转广告详情
                mPresenter.removeTimer();
                Uri content_url = Uri.parse(advUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, content_url);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 从广告详情重回启动页时，若显示的是广告页，则进入首页
        if (relAdv.getVisibility() == View.VISIBLE) {
            mPresenter.jump2Main(hasAdv);
        }
    }

    //解压zip成功
    @Override
    public void returnInitZip(String url) {
        this.startUrl = url;
    }

    //获取到广告信息
    @Override
    public void returnQueryAdvert(QueryAdvertBean.DataBean bean) {
        if (bean.getLeftShowNum() >= 0) {
            hasAdv = true;
//            ImageLoaderUtils.displayBigPhotoWithoutPlaceHolder(this, ivAdv, bean.getImgUrl());
            advUrl = bean.getLinkUrl();
        } else {
            hasAdv = false;
        }
    }

    //所有逻辑处理结束，跳转主页
    @Override
    public void returnJump2Main() {
//        MainActivity.startAction(OkGoTestActivity.this, true, true, startUrl, false, false);
//        finish();
    }

    //======================================================================================
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError(EmptyLayout.OnRetryListener onRetryListener) {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
    //======================================================================================
}
