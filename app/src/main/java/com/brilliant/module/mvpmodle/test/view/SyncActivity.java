package com.brilliant.module.mvpmodle.test.view;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.brilliant.R;
import com.brilliant.api.APIConstant;
import com.brilliant.base.BaseActivity;
import com.lzy.okgo.OkGo;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Response;

public class SyncActivity extends BaseActivity {

    @BindView(R.id.sync_content)
    TextView sync_content;

    private Handler handler = new InnerHandler();

    private class InnerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            try {
                Response response = (Response) msg.obj;
                //对response需要自己做解析
                String data = response.body().string();
                sync_content.setText(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        sync();
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
}
