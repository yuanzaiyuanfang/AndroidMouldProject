package com.brilliant.module.mvpmodle.okgotest.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.basemodule.utils.JsonUtils;
import com.brilliant.R;
import com.brilliant.base.BaseActivity;
import com.brilliant.module.mvpmodle.okgotest.bean.QueryAdvertBean;
import com.brilliant.module.mvpmodle.okgotest.contact.SplashAContract;
import com.brilliant.module.mvpmodle.okgotest.model.SplashAModel;
import com.brilliant.module.mvpmodle.okgotest.presenter.SplashAPresenter;
import com.brilliant.widget.NumberProgressBar;
import com.orhanobut.logger.Logger;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.ImagePickActivity;
import com.vincent.filepicker.filter.entity.ImageFile;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * description:
 * Date: 2017/4/6 10:50
 * User: Administrator
 */
public class OkGoRequestActivity extends BaseActivity<SplashAPresenter, SplashAModel> implements SplashAContract.View {

    //#################################################################### 自定义变量 start

    //===1.
    @BindView(R.id.title)
    TextView title;

    //===2.
    @BindView(R.id.imageView)
    ImageView imageView;

    //===3.
    @BindView(R.id.formUpload)
    Button btnFormUpload;

    @BindView(R.id.downloadSize)
    TextView tvDownloadSize;

    @BindView(R.id.tvProgress)
    TextView tvProgress;

    @BindView(R.id.netSpeed)
    TextView tvNetSpeed;

    @BindView(R.id.pbProgress)
    NumberProgressBar pbProgress;

    @BindView(R.id.images)
    TextView tvImages;

    private ArrayList<ImageFile> imageItems;

    //===4.
    @BindView(R.id.fileDownload)
    Button btnFileDownload;

    @BindView(R.id.download_downloadSize)
    TextView tvdownload_DownloadSize;

    @BindView(R.id.download_tvProgress)
    TextView tvdownload_Progress;

    @BindView(R.id.download_netSpeed)
    TextView tvdownload_NetSpeed;

    @BindView(R.id.download_pbProgress)
    NumberProgressBar pbdownload_Progress;

    //#################################################################### 自定义变量 end

    //#################################################################### 重写自定义方法 start

    @Override
    public int getLayoutId() {
        return R.layout.activity_okgo_post;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        SetTranslanteBar();
        //===
        mPresenter.queryAdvert(); //异步获取广告信息
        mPresenter.getBitmap();   // 获取图片
    }

    @Override
    public void initData() {
    }

    //获取到广告信息
    @Override
    public void returnQueryAdvert(QueryAdvertBean.DataBean bean) {
        title.setText(JsonUtils.toJson(bean));
    }

    @Override
    public void returnBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void returnUploadFile(String string) {
        btnFormUpload.setText("上传完成");
        Logger.i(string);
    }

    @Override
    public void returnDownloadFile(File file) {
        btnFileDownload.setText("下载完成");
        Logger.i(file.getAbsolutePath());
    }

    //#################################################################### 重写自定义方法 end

    //#################################################################### 自定义方法 start

    //#################################################################### 自定义方法 end

    //#################################################################### 重写系统方法 start

    @OnClick({R.id.selectImage, R.id.formUpload, R.id.fileDownload})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectImage:
                // 选择图片
                ImagePickActivity.start(OkGoRequestActivity.this, 5, ImagePickActivity.MODE_MULTIPLE,
                        true, true, false, ImagePickActivity.REQUEST_IMAGE);
                break;
            case R.id.formUpload:
                // 上传图片
                ArrayList<File> files = new ArrayList<>();
                if (imageItems != null && imageItems.size() > 0) {
                    for (int i = 0; i < imageItems.size(); i++) {
                        files.add(new File(imageItems.get(i).getPath()));
                    }
                }
                mPresenter.uploadFile(files);
                break;
            case R.id.fileDownload:
                // 文件下载
                mPresenter.downloadFile();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ImagePickActivity.REQUEST_IMAGE) {
            imageItems = data.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE);
            if (imageItems != null && imageItems.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < imageItems.size(); i++) {
                    if (i == imageItems.size() - 1)
                        sb.append("图片").append(i + 1).append(" ： ").append(imageItems.get(i).getPath());
                    else
                        sb.append("图片").append(i + 1).append(" ： ").append(imageItems.get(i).getPath()).append("\n");
                }
                tvImages.setText(sb.toString());
            } else {
                tvImages.setText("--");
            }
        }
    }

    //#################################################################### 重写系统方法 end
}
