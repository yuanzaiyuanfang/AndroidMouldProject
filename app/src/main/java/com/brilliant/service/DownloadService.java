package com.brilliant.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;

import com.blankj.utilcode.util.StringUtils;
import com.brilliant.R;
import com.brilliant.constant.APPConstant;
import com.brilliant.constant.APPMethod;
import com.brilliant.module.HomeActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * download service
 */
public class DownloadService extends Service {

    public static final String BUNDLE_KEY_DOWNLOAD_URL = "download_url";

    public static final String BUNDLE_KEY_TITLE = "title";

    private static final int NOTIFY_ID = 0;

    private int progress;

    private NotificationManager mNotificationManager;

    private boolean canceled;

    private String downloadUrl;

    private String mTitle = "正在下载%s";

    //文件保存路径
    private String saveFileName = APPConstant.DEFAULT_SAVE_FILE_PATH;

    private ICallbackResult callback;

    private DownloadBinder binder;

    private boolean serviceIsDestroy = false;

    private Context mContext = this;

    private Thread downLoadThread;

    private Notification mNotification;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                    // 下载完毕
                    mNotificationManager.cancel(NOTIFY_ID);
                    installApk();
                    break;
                }
                case 2: {
                    // 取消通知
                    mNotificationManager.cancel(NOTIFY_ID);
                    break;
                }
                case 1: {
                    Bundle bundle = msg.getData();
                    int rate = bundle.getInt("downloadCount", 0);
                    if (rate < 100) {
                        RemoteViews contentview = mNotification.contentView;
                        contentview.setTextViewText(R.id.tv_download_state, mTitle + "(" + rate + "%" + ")");
                        contentview.setProgressBar(R.id.pb_download, 100, rate, false);
                    } else {
                        // 下载完毕后变换通知形式
                        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
                        mNotification.contentView = null;
                        Intent intent = new Intent(mContext, HomeActivity.class);
                        // 告知已完成
                        intent.putExtra("completed", "yes");
                        // 更新参数,注意flags要使用FLAG_UPDATE_CURRENT
                        PendingIntent contentIntent = PendingIntent.getActivity(
                                mContext, 0, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
//					mNotification.setLatestEventInfo(mContext, "下载完成",
//							"文件已下载完毕", contentIntent);
                        serviceIsDestroy = true;
                        stopSelf();// 停掉服务自身
                    }
                    mNotificationManager.notify(NOTIFY_ID, mNotification);
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        downloadUrl = intent.getStringExtra(BUNDLE_KEY_DOWNLOAD_URL);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
//        downloadUrl = intent.getStringExtra(BUNDLE_KEY_DOWNLOAD_URL);
        saveFileName = saveFileName + APPConstant.APP_NAME;
        mTitle = String.format(mTitle, intent.getStringExtra(BUNDLE_KEY_TITLE));
        return binder;
    }

    private String getSaveFileName(String downloadUrl) {
        if (downloadUrl == null || StringUtils.isEmpty(downloadUrl)) {
            return "";
        }
        return downloadUrl.substring(downloadUrl.lastIndexOf("/"));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new DownloadBinder();
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        stopForeground(true);// 这个不确定是否有作用
    }

    private void startDownload() {
        canceled = false;
        downloadApk();
    }

    /**
     * 创建通知
     */
    private void setUpNotification() {
        int icon = R.mipmap.ic_launcher;
        CharSequence tickerText = "下载中...";
        long when = System.currentTimeMillis();
        mNotification = new Notification(icon, tickerText, when);

        // 放置在"正在运行"栏目中
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;

        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.download_notification_show);
        contentView.setTextViewText(R.id.tv_download_state, mTitle);
        // 指定个性化视图
        mNotification.contentView = contentView;

        Intent intent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 指定内容意图
        mNotification.contentIntent = contentIntent;
        mNotificationManager.notify(NOTIFY_ID, mNotification);
    }

    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    /**
     * 安装apk
     */
    private void installApk() {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        APPMethod.installAPK(mContext, apkfile);
    }

    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            File file = new File(APPConstant.DEFAULT_SAVE_FILE_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            String apkFile = saveFileName;
            File saveFile = new File(apkFile);
            try {
                downloadUpdateFile(downloadUrl, saveFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public long downloadUpdateFile(String downloadUrl, File saveFile)
            throws Exception {
        int downloadCount = 0;
        int currentSize = 0;
        long totalSize = 0;
        int updateTotalSize = 0;

        HttpURLConnection httpConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(downloadUrl);

            //=== 关键代码 解决https证书问题
            //ignore https certificate validation |忽略 https 证书验证
            if (url.getProtocol().toUpperCase().equals("HTTPS")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url
                        .openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                httpConnection = https;
            } else {
                httpConnection = (HttpURLConnection) url.openConnection();
            }

            //===
            httpConnection
                    .setRequestProperty("User-Agent", "PacificHttpClient");
            if (currentSize > 0) {
                httpConnection.setRequestProperty("RANGE", "bytes="
                        + currentSize + "-");
            }
            httpConnection.setConnectTimeout(10000);
            httpConnection.setReadTimeout(15000);
            updateTotalSize = httpConnection.getContentLength();
            if (httpConnection.getResponseCode() == 404) {
                throw new Exception("fail!");
            }
            is = httpConnection.getInputStream();
            fos = new FileOutputStream(saveFile, false);
            byte buffer[] = new byte[1024];
            int readsize = 0;
            while ((readsize = is.read(buffer)) > 0) {

                fos.write(buffer, 0, readsize);
                totalSize += readsize;
                // 为了防止频繁的通知导致应用吃紧，百分比增加10才通知一次
                if ((downloadCount == 0)
                        || (int) (totalSize * 100 / updateTotalSize) - 4 > downloadCount) {
                    downloadCount += 4;
                    // 更新进度
                    Message msg = mHandler.obtainMessage();
                    msg.what = 1;
                    //===
                    Bundle bundle = new Bundle();
                    bundle.putInt("downloadCount", downloadCount);
                    bundle.putLong("totalSize", totalSize);
                    bundle.putInt("updateTotalSize", updateTotalSize);
                    msg.setData(bundle); //mes利用Bundle传递数据
                    // msg.arg1 = downloadCount;
                    mHandler.sendMessage(msg);
                    if (callback != null)
                        callback.OnBackResult(progress);
                }
            }

            // 下载完成通知安装
            mHandler.sendEmptyMessage(0);
            // 下载完了，cancelled也要设置
            canceled = true;

        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
            if (is != null) {
                is.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
        return totalSize;
    }

    public class DownloadBinder extends Binder {
        public void start() {
            if (downLoadThread == null || !downLoadThread.isAlive()) {
                progress = 0;
                setUpNotification();
                new Thread() {
                    public void run() {
                        // 下载
                        startDownload();
                    }

                    ;
                }.start();
            }
        }

        public void cancel() {
            canceled = true;
        }

        public int getProgress() {
            return progress;
        }

        public boolean isCanceled() {
            return canceled;
        }

        public boolean serviceIsDestroy() {
            return serviceIsDestroy;
        }

        public void cancelNotification() {
            mHandler.sendEmptyMessage(2);
        }

        public void addCallback(ICallbackResult callback) {
            DownloadService.this.callback = callback;
        }
    }

    public static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        // Android use X509 cert
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}
