package com.brilliant.module.mvpmodle.test.view;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.brilliant.R;
import com.brilliant.base.BaseActivity;
import com.brilliant.util.MyToastUtil;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 一次性申请一个权限 (准对6.0及以上系统的手机，否则没有效果)
 *
 * @RuntimePermissions 标记需要运行时判断的类
 * @NeedsPermission 标记需要检查权限的方法
 * @OnShowRationale 授权提示回调
 * @OnPermissionDenied 授权被拒绝回调
 * @OnNeverAskAgain 授权不再拒绝不再显示回调
 * <p>
 * 1.添加 @RuntimePermissions 在Activity或者Fragment中需要添加，来处理权限的问题
 * 3.将需要权限的操作定义在一个方法里，并用 @NeedsPermission(Manifest.permission.CAMERA)表明需要的权限(可以是多个)
 * 4.编译一下，就会生成【当前类名+PermissionsDispatcher】的类，在原本调用的地方调用@NeedsPermission标记的方法，
 * 5.这时候你会发现会对应生成【方法名+WithCheck】的方法
 * <p>
 */
@RuntimePermissions
public class RequestPermissionSingleActivity extends BaseActivity {

    private static final int CALL_CAMERA_REQUEST_CODE = 1;

    private TextView tvCallPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_request_permission_single;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvCallPhone = (TextView) findViewById(R.id.tv_call_phone);
        tvCallPhone.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_call_phone:
                RequestPermissionSingleActivityPermissionsDispatcher.showCameraWithCheck(this);
                break;
        }
    }

    /**
     * 如果用户拥有该权限执行的方法 ,方法前面不能带有private修饰符,方法名自定义
     */
    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, CALL_CAMERA_REQUEST_CODE);
    }

    /**
     *  方法前面不能带有private修饰符,方法名自定义
     * @param request
     */
    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog("使用此功能需要打开照相机的权限", request);
    }

    /**
     * 如果用户拒绝该权限执行的方法 ,方法前面不能带有private修饰符,方法名自定义
     */
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        MyToastUtil.showShortToast("你拒绝了权限，该功能不可用");
    }

    /**
     * 如果用户选择了让设备“不再询问”，而调用的方法 ,方法前面不能带有private修饰符,方法名自定义
     */
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNeverAskAgain() {
        MyToastUtil.showShortToast("不再允许询问该权限，该功能不可用");
    }

    /**
     * 权限请求回调，提示用户之后，用户点击“允许”或者“拒绝”之后调用此方法
     *
     * @param requestCode  定义的权限编码
     * @param permissions  权限名称
     * @param grantResults 允许/拒绝
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RequestPermissionSingleActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * @param messageResId
     * @param request
     */
    private void showRationaleDialog(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

}
