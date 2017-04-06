package com.brilliant.demo;

import android.content.Context;

/**
 * Created by Conan on 2017/2/23.
 */

/****************
 * 使用例子
 ****************/
//  API.getInstance().errorObservable("失败");
//  API.getInstance().demoMethod(mContext, params...).flatMap(Func...).map(Func...).compose(RxSchedulers.<Object>io_main());

public class DemoModel implements DemoContract.Model {

    Context mContext;

    @Override
    public void setTag(Context context) {
        mContext = context;
    }

}
