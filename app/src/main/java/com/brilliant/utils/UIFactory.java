package com.brilliant.utils;

import android.app.Activity;
import android.content.Intent;

import com.brilliant.module.GuideActivity;
import com.brilliant.module.HomeActivity;
import com.brilliant.module.mvpmodle.news.NewsActivity;

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

    /** home页面
     * @param activity
     * @param requestCode
     */
    public static void startHomeActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /** 新闻测试页面
     * @param activity
     * @param requestCode
     */
    public static void startNewsActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, NewsActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }
}
