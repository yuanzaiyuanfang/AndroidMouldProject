package com.brilliant.module.mvpmodle.test.view;

import android.content.Intent;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.basemodule.okgo.model.LzyResponse;
import com.brilliant.R;
import com.brilliant.api.APIConstant;
import com.brilliant.api.APIMethod;
import com.brilliant.api.JsonCallback;
import com.brilliant.base.BaseActivity;
import com.brilliant.module.mvpmodle.test.bean.ServerModel;
import com.brilliant.widget.NumberProgressBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.ImagePickActivity;
import com.vincent.filepicker.filter.entity.ImageFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.brilliant.R.id.formUpload;

public class FormUploadActivity extends BaseActivity {

    @BindView(R.id.description)
    TextView description;

    @BindView(formUpload)
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_form_upload;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        description.setText("1.支持上传单个文件\n" +
                "2.支持同时上传多个文件\n" +
                "3.支持多个文件多个参数同时上传\n" +
                "4.支持大文件上传,无论多大都不会发生OOM\n" +
                "5.支持监听上传进度和上传网速");
    }

    @OnClick({R.id.selectImage, R.id.formUpload})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectImage:
                // 选择图片
                ImagePickActivity.start(FormUploadActivity.this, 5, ImagePickActivity.MODE_MULTIPLE,
                        true, true, false, ImagePickActivity.REQUEST_IMAGE);
                break;
            case R.id.formUpload:
                // 上传图片
                formUpload();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

    /**
     *
     */
    public void formUpload() {
        ArrayList<File> files = new ArrayList<>();
        if (imageItems != null && imageItems.size() > 0) {
            for (int i = 0; i < imageItems.size(); i++) {
                files.add(new File(imageItems.get(i).getPath()));
            }
        }
        //=== 参数设置
        HashMap<String, String> params = new HashMap<>();
        APIMethod.formUploadRequestWithCallback(FormUploadActivity.this, APIConstant.URL_FORM_UPLOAD,
                params, files, new JsonCallback<LzyResponse<ServerModel>>() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        btnFormUpload.setText("正在上传中...");
                    }

                    @Override
                    public void onSuccess(LzyResponse<ServerModel> responseData, Call call, Response response) {
                        btnFormUpload.setText("上传完成");
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        btnFormUpload.setText("上传出错");
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        Logger.i("upProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);

                        String downloadLength = Formatter.formatFileSize(getApplicationContext(), currentSize);
                        String totalLength = Formatter.formatFileSize(getApplicationContext(), totalSize);
                        tvDownloadSize.setText(downloadLength + "/" + totalLength);
                        String netSpeed = Formatter.formatFileSize(getApplicationContext(), networkSpeed);
                        tvNetSpeed.setText(netSpeed + "/S");
                        tvProgress.setText((Math.round(progress * 10000) * 1.0f / 100) + "%");
                        pbProgress.setMax(100);
                        pbProgress.setProgress((int) (progress * 100));
                    }
                });
    }
}