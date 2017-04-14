package com.brilliant.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.Utils;
import com.brilliant.R;

/**
 * description:
 * Date: 2017/4/13 16:48
 * User: Administrator
 */
public class MyToastUtil {

    private MyToastUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static Toast sToast;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private static boolean isJumpWhenMore;

    private static boolean isCustom;

    private static CharSequence lastToast = "";

    private static long lastToastTime;

    /**
     * 吐司初始化
     *
     * @param isCustom       是否个性化
     * @param isJumpWhenMore 当连续弹出吐司时，是要弹出新吐司还是只修改文本内容
     *                       <p>{@code true}: 弹出新吐司<br>{@code false}: 只修改文本内容</p>
     *                       <p>如果为{@code false}的话可用来做显示任意时长的吐司</p>
     */
    public static void init(boolean isCustom, boolean isJumpWhenMore) {
        MyToastUtil.isJumpWhenMore = isJumpWhenMore;
        MyToastUtil.isCustom = isCustom;
    }

    /**
     * 安全地显示短时吐司
     *
     * @param text 文本
     */
    public static void showShortToastSafe(final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(text, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     */
    public static void showShortToastSafe(final @StringRes int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(resId, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showShortToastSafe(final @StringRes int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(resId, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 安全地显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showShortToastSafe(final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(format, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 安全地显示长时吐司
     *
     * @param text 文本
     */
    public static void showLongToastSafe(final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(text, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     */
    public static void showLongToastSafe(final @StringRes int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(resId, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showLongToastSafe(final @StringRes int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(resId, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * 安全地显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showLongToastSafe(final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(format, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * 显示短时吐司
     *
     * @param text 文本
     */
    public static void showShortToast(CharSequence text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     */
    public static void showShortToast(@StringRes int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showShortToast(@StringRes int resId, Object... args) {
        showToast(resId, Toast.LENGTH_SHORT, args);
    }

    /**
     * 显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showShortToast(String format, Object... args) {
        showToast(format, Toast.LENGTH_SHORT, args);
    }

    /**
     * 显示长时吐司
     *
     * @param text 文本
     */
    public static void showLongToast(CharSequence text) {
        showToast(text, Toast.LENGTH_LONG);
    }

    /**
     * 显示长时吐司
     *
     * @param resId 资源Id
     */
    public static void showLongToast(@StringRes int resId) {
        showToast(resId, Toast.LENGTH_LONG);
    }

    /**
     * 显示长时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showLongToast(@StringRes int resId, Object... args) {
        showToast(resId, Toast.LENGTH_LONG, args);
    }

    /**
     * 显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showLongToast(String format, Object... args) {
        showToast(format, Toast.LENGTH_LONG, args);
    }

    /**
     * 显示吐司
     *
     * @param resId    资源Id
     * @param duration 显示时长
     */
    private static void showToast(@StringRes int resId, int duration) {
        showToast(Utils.getContext().getResources().getText(resId).toString(), duration);
    }

    /**
     * 显示吐司
     *
     * @param resId    资源Id
     * @param duration 显示时长
     * @param args     参数
     */
    private static void showToast(@StringRes int resId, int duration, Object... args) {
        showToast(String.format(Utils.getContext().getResources().getString(resId), args), duration);
    }

    /**
     * 显示吐司
     *
     * @param format   格式
     * @param duration 显示时长
     * @param args     参数
     */
    private static void showToast(String format, int duration, Object... args) {
        showToast(String.format(format, args), duration);
    }

    /**
     * 显示吐司
     *
     * @param text     文本
     * @param duration 显示时长
     */
    private static void showToast(CharSequence text, int duration) {
        if (isJumpWhenMore) cancelToast();
        if (sToast == null) {
            if (isCustom) {
                sToast = getCustomToast(Utils.getContext(), text, duration);
            } else {
                sToast = Toast.makeText(Utils.getContext(), text, duration);
            }
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        if (sToast != null) {
            sToast.show();
        }
    }

    /**
     * 取消吐司显示
     */
    public static void cancelToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }

    /**
     * 展示自定义toast
     *
     * @param context
     * @param message
     * @param duration
     * @return
     */
    public static Toast getCustomToast(Context context, CharSequence message, int duration) {
        if (message != null) {
            long time = System.currentTimeMillis();
            if (!message.equals(lastToast) || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                Toast toast = new Toast(context);
                toast.setView(view);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(duration);
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
                return toast;
            }
        }
        return null;
    }
}
