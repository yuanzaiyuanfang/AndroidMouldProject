package com.brilliant.local.sharePref;

import android.content.Context;


/**
 * <用户信息缓存>
 *
 * @author caoyinfei
 * @version [版本号, 2016/6/6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EBSharedPrefVersion extends BaseSharedPreference {

    public EBSharedPrefVersion(Context context, String fileName) {
        super(context, fileName);
    }

    public static final String PREFS_PREVERSION = "prefs_preversion"; // 标注版本号

}
