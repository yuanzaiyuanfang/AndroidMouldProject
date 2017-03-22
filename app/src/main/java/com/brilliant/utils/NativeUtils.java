package com.brilliant.utils;

import android.text.TextUtils;

/**
 * description:
 * Date: 2017/3/22 11:21
 * User: Administrator
 */
public class NativeUtils {

    /**
     * 裁剪图集ID
     *
     * @param photoId
     * @return
     */
    public static String clipPhotoSetId(String photoId) {
        if (TextUtils.isEmpty(photoId)) {
            return photoId;
        }
        int i = photoId.indexOf("|");
        if (i >= 4) {
            String result = photoId.replace('|', '/');
            return result.substring(i - 4);
        }
        return null;
    }
}
