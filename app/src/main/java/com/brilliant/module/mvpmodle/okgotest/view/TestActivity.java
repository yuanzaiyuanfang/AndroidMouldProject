package com.brilliant.module.mvpmodle.okgotest.view;

import android.view.View;

import com.brilliant.R;
import com.brilliant.base.BaseActivity;
import com.brilliant.constant.UIFactory;

import butterknife.OnClick;

/**
 * activity模板，所有新建的activity都应该复制该文件然后自定义
 * description:
 * Date: 2017/2/13 11:10
 * User: Administrator
 */
public class TestActivity extends BaseActivity {

    //#################################################################### 自定义变量 start


    //#################################################################### 自定义变量 end

    //#################################################################### 重写自定义方法 start

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    //#################################################################### 重写自定义方法 end

    //#################################################################### 自定义方法 start

    //#################################################################### 自定义方法 end

    //#################################################################### 重写系统方法 start

    @OnClick({R.id.okgo_request, R.id.sync, R.id.upload, R.id.download})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okgo_request:
                //  okgo 网络请求测试
                UIFactory.startOkGoTestActivity(this, RESULT_OK);
                break;
            case R.id.sync:
                // 同步请求
                UIFactory.startSyncActivity(this, RESULT_OK);
                break;
            case R.id.upload:
                // 文件上传
                UIFactory.startFormUploadActivity(this, RESULT_OK);
                break;
            case R.id.download:
                // 文件下载
                UIFactory.startFileDownloadActivity(this, RESULT_OK);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        backPressConform();
    }

    /**
     * @Description
     * @author BrillantZhao
     * @date 2015-1-16 下午12:56:28
     */
    private void backPressConform() {
        finish();
    }

    //#################################################################### 重写系统方法 end
}
