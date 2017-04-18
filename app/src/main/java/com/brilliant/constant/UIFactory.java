package com.brilliant.constant;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.brilliant.module.GuideActivity;
import com.brilliant.module.HomeActivity;
import com.brilliant.module.mvpmodle.okgotest.view.FileDownloadActivity;
import com.brilliant.module.mvpmodle.okgotest.view.FormUploadActivity;
import com.brilliant.module.mvpmodle.okgotest.view.OkGoRequestActivity;
import com.brilliant.module.mvpmodle.okgotest.view.SyncActivity;
import com.brilliant.module.mvpmodle.okgotest.view.TestActivity;
import com.brilliant.service.DownloadService;
import com.brilliant.service.ICallbackResult;

/**
 * description:
 * Date: 2017/3/22 16:47
 * User: Administrator
 */
public class UIFactory {

    /**
     * 跳转到引导页面
     *
     * @param activity
     * @param requestCode
     */
    public static void startGuideActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, GuideActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * home页面
     *
     * @param activity
     * @param requestCode
     */
    public static void startHomeActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 进入测试页面
     *
     * @param activity
     * @param requestCode
     */
    public static void startTestActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, TestActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * OKGO网络请求测试页面
     *
     * @param activity
     * @param requestCode
     */
    public static void startOkGoTestActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, OkGoRequestActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 同步请求测试页面
     *
     * @param activity
     * @param requestCode
     */
    public static void startSyncActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, SyncActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 文件上传测试页面
     *
     * @param activity
     * @param requestCode
     */
    public static void startFormUploadActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, FormUploadActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 文件下载测试页面
     *
     * @param activity
     * @param requestCode
     */
    public static void startFileDownloadActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, FileDownloadActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开升级
     *
     * @param context
     * @param downurl
     * @param tilte
     */
    public static void openDownLoadService(Context context, String downurl, String tilte) {
        final ICallbackResult callback = new ICallbackResult() {
            @Override
            public void OnBackResult(Object s) {
            }
        };

        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
                binder.addCallback(callback);
                binder.start();
            }
        };
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, downurl);
        intent.putExtra(DownloadService.BUNDLE_KEY_TITLE, tilte);
        context.startService(intent);
        context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
}
