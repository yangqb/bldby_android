package com.bldby.loginlibrary.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityLoginBinding;

/*
 * 登录页面
 * */

@Route(path = RouteLoginConstants.LOGINMAIN)
public class LoginActivity extends BaseUiActivity {

    private ActivityLoginBinding bingding;
    private String phone = "";
    private String code = "";

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        bingding = ActivityLoginBinding.inflate(layoutInflater, viewGroup, false);
        bingding.setViewModel(this);
        return bingding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(getString(R.string.app_name));
        setTitleBackground(R.color.white);
        bingding.loginBtn.setEnabled(false);
        String str1 = "登录即视为您已经阅读并同意";
        String str2 = "用户协议";
        String str3 = "和";
        String str4 = "隐私政策";
        setSpannableString(str1, str2, str3, str4);
    }

    @Override
    public void loadData() {

    }

    public void onClick(View view) {
        start(RouteLoginConstants.BIDINGACCOUNT);
    }

    public void onClickRegister(View view) {
        start(RouteLoginConstants.REGISTER);
    }

    public void onClickLogin(String phone, String password) {
        bingding.loginBtn.setEnabled(false);
        ToastUtil.show(phone + "========" + password);
    }

    @Override
    public void initListener() {
        bingding.editAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    bingding.loginBtn.setEnabled(true);
                } else {
                    bingding.loginBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bingding.editVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                code = s.toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    bingding.loginBtn.setEnabled(true);
                } else {
                    bingding.loginBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void setSpannableString(String str1, String str2, String str3, String str4) {
        bingding.tvProtocolsPrivate.setText("");
        SpannableString span1 = new SpannableString(str1);
        SpannableString span2 = new SpannableString(str2);
        SpannableString span3 = new SpannableString(str3);
        SpannableString span4 = new SpannableString(str4);
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.parseColor("#999999"));
        span1.setSpan(new AbsoluteSizeSpan(12, true), 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span1.setSpan(colorSpan1, 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.parseColor("#4F93FF"));
        span2.setSpan(new AbsoluteSizeSpan(12, true), 0, str2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span2.setSpan(colorSpan2, 0, str2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if (widget instanceof TextView) {
                    ((TextView) widget).setHighlightColor(Color.TRANSPARENT);
                }
            }

            //去除连接下划线
            @Override
            public void updateDrawState(TextPaint ds) {
                /**set textColor**/
                ds.setColor(Color.parseColor("#4F93FF"));
                /**Remove the underline**/
                ds.setUnderlineText(false);
                ds.clearShadowLayer();
            }
        }, 0, span2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        span3.setSpan(new AbsoluteSizeSpan(12, true), 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span3.setSpan(colorSpan1, 0, str3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);


        span4.setSpan(new AbsoluteSizeSpan(12, true), 0, str4.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span4.setSpan(colorSpan2, 0, str4.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span4.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if (widget instanceof TextView) {
                    ((TextView) widget).setHighlightColor(Color.TRANSPARENT);
                }

            }

            //去除连接下划线
            @Override
            public void updateDrawState(TextPaint ds) {
                /**set textColor**/
                ds.setColor(Color.parseColor("#4F93FF"));
                /**Remove the underline**/
                ds.setUnderlineText(false);
                ds.clearShadowLayer();
            }
        }, 0, span4.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        bingding.tvProtocolsPrivate.append(span1);
        bingding.tvProtocolsPrivate.append(span2);
        bingding.tvProtocolsPrivate.append(span3);
        bingding.tvProtocolsPrivate.append(span4);
        bingding.tvProtocolsPrivate.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
