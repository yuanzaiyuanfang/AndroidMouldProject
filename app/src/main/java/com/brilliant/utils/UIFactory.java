package com.brilliant.utils;

import android.app.Activity;
import android.content.Intent;

import com.brilliant.module.GuideActivity;
import com.brilliant.module.HomeActivity;

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
     * @param activity
     * @param requestCode
     */
    public static void startHomeActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }
}
