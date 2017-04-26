package com.brilliant.module.mvpmodle.test.view;


import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.brilliant.R;
import com.brilliant.base.BaseActivity;
import com.brilliant.util.MyToastUtil;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 一次性申请多个权限  (准对6.0及以上系统的手机，否则没有效果)
 *
 * @RuntimePermissions 标记需要运行时判断的类
 * @NeedsPermission 标记需要检查权限的方法
 * @OnShowRationale 授权提示回调
 * @OnPermissionDenied 授权被拒绝回调
 * @OnNeverAskAgain 授权不再拒绝不再显示回调
 * <p>
 * 1.@RuntimePermissions 在Activity或者Fragment中需要添加，来处理权限的问题
 * 2.需要权限的时候，先要检查是否拥有该权限 (MyDeatilsActivity 替换成当前的类(Activity或者Fragment))
 * 3.将需要权限的操作定义在一个方法里，并用 @NeedsPermission(Manifest.permission.CAMERA)表明需要的权限(可以是多个)
 * 4.编译一下，就会生成【当前类名+PermissionsDispatcher】的类，在原本调用的地方调用@NeedsPermission标记的方法，
 * 这时候你会发现会对应生成【方法名+WithCheck】的方法
 * <p>
 */
@RuntimePermissions
public class RequestPermissionMultipleActivity extends BaseActivity {

    private TextView tvReadSMS;

    @Override
    public int getLayoutId() {
        return R.layout.activity_request_permission_multiple;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvReadSMS = (TextView) findViewById(R.id.tv_read_sms);
        tvReadSMS.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_read_sms:
                RequestPermissionMultipleActivityPermissionsDispatcher.readSMSWithCheck(this);
                break;
        }
    }

    /**
     * 如果用户拥有该权限执行的方法 ,方法前面不能带有private修饰符 ,方法前面不能带有private修饰符,方法名自定义
     */
    @NeedsPermission({Manifest.permission.READ_SMS, Manifest.permission.READ_EXTERNAL_STORAGE})
    void readSMS() {
        String sms = getSmsInPhone();
        Log.d("sms", sms);
    }

    /**
     *  方法前面不能带有private修饰符,方法名自定义
     *
     * @param request
     */
    @OnShowRationale({Manifest.permission.READ_SMS, Manifest.permission.READ_EXTERNAL_STORAGE})
    void noPermission(PermissionRequest request) {
        showRationaleDialog("必须有权限才能使用", request);
    }

    /**
     * 如果用户拒绝该权限执行的方法 ,方法前面不能带有private修饰符,方法名自定义
     */
    @OnPermissionDenied({Manifest.permission.READ_SMS, Manifest.permission.READ_EXTERNAL_STORAGE})
    void juJue() {
        MyToastUtil.showShortToast("用户拒绝了申请");
    }

    /**
     * 如果用户选择了让设备“不再询问”，而调用的方法 ,方法前面不能带有private修饰符,方法名自定义
     */
    @OnNeverAskAgain({Manifest.permission.READ_SMS, Manifest.permission.READ_EXTERNAL_STORAGE})
    void juJueAndNoTiShi() {
        MyToastUtil.showShortToast("拒绝且不提示");
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
        RequestPermissionMultipleActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * @return
     */
    public String getSmsInPhone() {
        final String SMS_URI_ALL = "content://sms/";
        final String SMS_URI_INBOX = "content://sms/inbox";
        final String SMS_URI_SEND = "content://sms/sent";
        final String SMS_URI_DRAFT = "content://sms/draft";

        StringBuilder smsBuilder = new StringBuilder();

        try {
            ContentResolver cr = getContentResolver();
            String[] projection = new String[]{"_id", "address", "person",
                    "body", "date", "type"};
            Uri uri = Uri.parse(SMS_URI_ALL);
            Cursor cur = cr.query(uri, projection, null, null, "date desc");

            if (cur.moveToFirst()) {
                String name;
                String phoneNumber;
                String smsbody;
                String date;
                String type;

                int nameColumn = cur.getColumnIndex("person");
                int phoneNumberColumn = cur.getColumnIndex("address");
                int smsbodyColumn = cur.getColumnIndex("body");
                int dateColumn = cur.getColumnIndex("date");
                int typeColumn = cur.getColumnIndex("type");

                do {
                    name = cur.getString(nameColumn);
                    phoneNumber = cur.getString(phoneNumberColumn);
                    smsbody = cur.getString(smsbodyColumn);

                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd hh:mm:ss");
                    Date d = new Date(Long.parseLong(cur.getString(dateColumn)));
                    date = dateFormat.format(d);

                    int typeId = cur.getInt(typeColumn);
                    if (typeId == 1) {
                        type = "接收";
                    } else if (typeId == 2) {
                        type = "发送";
                    } else {
                        type = "";
                    }

                    smsBuilder.append("[");
                    smsBuilder.append(name + ",");
                    smsBuilder.append(phoneNumber + ",");
                    smsBuilder.append(smsbody + ",");
                    smsBuilder.append(date + ",");
                    smsBuilder.append(type);
                    smsBuilder.append("] ");

                    if (smsbody == null) smsbody = "";
                } while (cur.moveToNext());
            } else {
                smsBuilder.append("no result!");
            }
            smsBuilder.append("getSmsInPhone has executed!");
        } catch (SQLiteException ex) {
            Logger.d(ex.getMessage());
        }
        return smsBuilder.toString();
    }

    /**
     * @param messageResId
     * @param request
     */
    private void showRationaleDialog(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("算了吧", new DialogInterface.OnClickListener() {
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
