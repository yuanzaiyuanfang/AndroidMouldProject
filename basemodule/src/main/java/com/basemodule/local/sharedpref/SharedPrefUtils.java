package com.basemodule.local.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.basemodule.base.IBaseApplication;
import com.basemodule.utils.NativeUtil;
import com.orhanobut.hawk.Hawk;

/**
 * 对SharedPreference文件中的各种类型的数据进行存取操作
 */
public class SharedPrefUtils {

    private static SharedPreferences sp;

    /**
     * 初始化
     *
     * @param context
     */
    private static void init(Context context) {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(IBaseApplication.getAppInstance());
        }
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void setSharedIntData(Context context, String key, int value) {
        if (sp == null) {
            init(context);
        }
        sp.edit().putInt(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getSharedIntData(Context context, String key, int defValue) {
        if (sp == null) {
            init(context);
        }
        return sp.getInt(key, defValue);
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void setSharedlongData(Context context, String key, long value) {
        if (sp == null) {
            init(context);
        }
        sp.edit().putLong(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static long getSharedlongData(Context context, String key, long defValue) {
        if (sp == null) {
            init(context);
        }
        return sp.getLong(key, defValue);
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void setSharedFloatData(Context context, String key, float value) {
        if (sp == null) {
            init(context);
        }
        sp.edit().putFloat(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Float getSharedFloatData(Context context, String key, Float defValue) {
        if (sp == null) {
            init(context);
        }
        return sp.getFloat(key, defValue);
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void setSharedBooleanData(Context context, String key, boolean value) {
        if (sp == null) {
            init(context);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Boolean getSharedBooleanData(Context context, String key, boolean defValue) {
        if (sp == null) {
            init(context);
        }
        return sp.getBoolean(key, defValue);
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void setSharedStringData(Context context, String key, String value) {
        if (sp == null) {
            init(context);
        }
        sp.edit().putString(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getSharedStringData(Context context, String key, String defValue) {
        if (sp == null) {
            init(context);
        }
        return sp.getString(key, defValue);
    }

    /**
     * 使用hawk存储对象
     *
     * @param key
     * @param value
     */
    public static void setSharedObject(String key, Object value) {
        Hawk.put(key, value);
    }

    /**
     * @param key
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T getSharedObject(String key, T defaultValue) {
        if (!NativeUtil.isEmpty(key) && Hawk.contains(key)) {
            return Hawk.get(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    /**
     * @param key
     */
    public static void deleteSharedObject(String key) {
        if (!NativeUtil.isEmpty(key) && Hawk.contains(key)) {
            Hawk.delete(key);
        }
    }

    /**
     * @param key
     * @return
     */
    public static boolean containsSharedObject(String key) {
        return Hawk.contains(key);
    }

    /**
     * @return
     */
    public static long getSharedObjectCount() {
        return Hawk.count();
    }

    /**
     *
     */
    public static void clearSharedObject() {
        Hawk.deleteAll();
    }
}