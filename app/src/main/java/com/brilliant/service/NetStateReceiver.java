package com.brilliant.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orhanobut.logger.Logger;

public class NetStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Logger.i("wifi网络！");
                ActivityObserveManager.getInstance().notifyRefresh();
            } else if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                Logger.i("手机网络！");
                ActivityObserveManager.getInstance().notifyRefresh();
            } else {
                Logger.i("网络未连接！");
            }
        } else {
            Logger.i("网络断开！");
        }
    }


}
