package com.brilliant.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.basemodule.utils.NativeUtil;
import com.brilliant.R;

public class CustomProgressDialog extends Dialog {

    private Context context = null;

    private TextView loadingNotice;

    public CustomProgressDialog(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
        initView(context);
    }

    public void initView(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog, null);
        loadingNotice = (TextView) root.findViewById(R.id.loading_notice);
        ImageView imageView = (ImageView) root.findViewById(R.id.loadingImageView);
        setContentView(root);
        getWindow().getAttributes().gravity = Gravity.CENTER;
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    public void onWindowFocusChanged(boolean hasFocus) {

    }

    /**
     * @param text
     */
    public void showNotice(String text) {
        if (NativeUtil.isEmpty(text) || "".equals(text)) {
            loadingNotice.setVisibility(View.GONE);
        } else {
            loadingNotice.setVisibility(View.VISIBLE);
            loadingNotice.setText(text);
        }
        show();
    }

}
