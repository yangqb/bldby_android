package com.bldby.airticket.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bldby.airticket.R;
import com.lxj.xpopup.core.CenterPopupView;

/**
 * package name: com.bldby.airticket.view
 * user: yangqinbo
 * date: 2020/6/20
 * time: 11:56
 * email: 694125155@qq.com
 */
public class CustomDialog extends CenterPopupView implements View.OnClickListener {
    public OnCancelListener onCancelListener;
    public OnConfirmListener onConfirmListener;
    boolean isHideCancel = false;
    private String title;
    private String content;
    private String cancelStr;
    private String confirmStr;

    public interface OnConfirmListener {
        void onConfirm();
    }

    public interface OnCancelListener {
        void onCancel();
    }

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_layout_text;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvContent = findViewById(R.id.tv_content);
        TextView tvCancel = findViewById(R.id.tv_cancel);
        TextView tvConfirm = findViewById(R.id.tv_confirm);
        View lineVer = findViewById(R.id.line_ver);
        tvCancel.setOnClickListener(this::onClick);
        tvConfirm.setOnClickListener(this::onClick);
        View line1 = findViewById(R.id.line1);
        View line2 = findViewById(R.id.line2);
        if (title == null || TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(GONE);
            lineVer.setVisibility(GONE);
        } else {
            tvTitle.setText(title);
        }

        if (content == null || TextUtils.isEmpty(content)) {
            tvContent.setVisibility(GONE);
            line1.setVisibility(GONE);
        } else {
            tvContent.setText(content);
        }

        if (confirmStr == null) {
            tvConfirm.setVisibility(GONE);
            line2.setVisibility(GONE);
        } else {
            tvConfirm.setText(confirmStr);
        }

        if (cancelStr == null || isHideCancel) {
            tvCancel.setVisibility(GONE);
            line2.setVisibility(GONE);
        } else {
            tvCancel.setText(cancelStr);
        }

        if (cancelStr == null && confirmStr == null) {
            line1.setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel) {
            if (onCancelListener != null) onCancelListener.onCancel();
            dismiss();
        } else if (v.getId() == R.id.tv_confirm) {
            if (onConfirmListener != null) onConfirmListener.onConfirm();
            if (popupInfo.autoDismiss) dismiss();
        }
    }

    public CustomDialog hideCancelBtn() {
        isHideCancel = true;
        return this;
    }

    public CustomDialog setListener(OnConfirmListener confirmListener, OnCancelListener cancelListener) {
        this.onCancelListener = cancelListener;
        this.onConfirmListener = confirmListener;
        return this;
    }

    public CustomDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public CustomDialog setConfirmText(String confirmStr) {
        this.confirmStr = confirmStr;
        return this;
    }

    public CustomDialog setCancelText(String cancelStr) {
        this.cancelStr = cancelStr;
        return this;
    }
}
