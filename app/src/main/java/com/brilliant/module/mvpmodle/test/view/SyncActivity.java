package com.brilliant.module.mvpmodle.test.view;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.brilliant.R;
import com.brilliant.api.APIConstant;
import com.brilliant.api.StringDialogCallback;
import com.brilliant.base.BaseActivity;
import com.lzy.okgo.OkGo;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class SyncActivity extends BaseActivity {

    @BindView(R.id.sync_content)
    TextView sync_content;

    private Handler handler = new InnerHandler();

    private class InnerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //对response需要自己做解析
            sync_content.setText((String) msg.obj);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sync;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        // 同步请求
        //  sync();

        // https请求测试
        httpsSyncTest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }

    /**
     */
    public void sync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //同步会阻塞主线程，必须开线程
                    Response response = OkGo.get(APIConstant.URL_JSONOBJECT)//
                            .tag(this)//
                            .headers("header1", "headerValue1")//
                            .params("param1", "paramValue1")//
                            .execute();  //不传callback即为同步请求

                    Message message = Message.obtain();
                    message.obj = response;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     *
     */
    public void httpsSyncTest() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //同步会阻塞主线程，必须开线程
//                    // https://172.16.88.230:8383/apiv1/app/listAllProds?pageNo=1&platform=2
//                    Response response = OkGo.get("https://172.16.88.230:8383/apiv1/app/listAllProds")//
//                            .tag(this)//
//                            .headers("header1", "headerValue1")//
//                            .params("pageNo", "1")//
//                            .params("platform", "2")//
//                            .execute();  //不传callback即为同步请求
//
//                    Message message = Message.obtain();
//                    message.obj = response;
//                    handler.sendMessage(message);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        OkGo.get("https://172.16.88.230:8383/apiv1/app/listAllProds")//
                .tag(this)//
                .headers("header1", "headerValue1")//
                .params("pageNo", "1")//
                .params("platform", "2")//
                .execute(new StringDialogCallback(this) {
                    @Override
                    public void onSuccess(String data, Call call, Response response) {
                        Message message = Message.obtain();
                        message.obj = data;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        // handleError(call, response);
                    }
                });
    }
}
