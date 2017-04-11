package com.brilliant.module.mvpmodle.okgotest.view;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.basemodule.utils.ImageLoaderUtils;
import com.brilliant.R;
import com.brilliant.base.BaseActivity;
import com.brilliant.module.mvpmodle.okgotest.bean.QueryAdvertBean;
import com.brilliant.module.mvpmodle.okgotest.contact.SplashAContract;
import com.brilliant.module.mvpmodle.okgotest.model.SplashAModel;
import com.brilliant.module.mvpmodle.okgotest.presenter.SplashAPresenter;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * description:
 * Date: 2017/4/6 10:50
 * User: Administrator
 */
public class OkGoTestActivity extends BaseActivity<SplashAPresenter, SplashAModel> implements SplashAContract.View{

    private static final String TAG = "OkGoTestActivity";

    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @BindView(R.id.iv_splash_r)
    ImageView ivSplashR;

    @BindView(R.id.splash_view)
    RelativeLayout splashView;

    @BindView(R.id.iv_adv)
    ImageView ivAdv;

    @BindView(R.id.tv_skip)
    TextView tvSkip;

    @BindView(R.id.rel_adv)
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
            ImageLoaderUtils.displayBigPhotoWithoutPlaceHolder(this, ivAdv, bean.getImgUrl());
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
}
