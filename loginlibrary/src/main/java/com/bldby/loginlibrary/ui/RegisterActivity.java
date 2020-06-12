package com.bldby.loginlibrary.ui;

import android.graphics.Color;
import android.os.CountDownTimer;
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
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.app.util.RegUtils;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityRegisterBinding;

/**
 * package name: com.bldby.loginlibrary.ui
 * user: yangqinbo
 * date: 2020/6/11
 * time: 18:39
 * email: 694125155@qq.com
 */
/*
 * 注册页面
 * */
@Route(path = RouteLoginConstants.REGISTER)
public class RegisterActivity extends BaseUiActivity {
    private ActivityRegisterBinding binding;
    private String phone = "";
    private String code = "";

    private CountDownTimer mTimer = new CountDownTimer(6000 * 10, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            binding.btnCode.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            binding.btnCode.setEnabled(true);
            binding.btnCode.setText(R.string.login_biding_get_code);
        }
    };

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityRegisterBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(getString(R.string.register_title));
        setTitleBackground(R.color.white);
        binding.loginBtn.setEnabled(false);
        String str1 = "已阅读并同意";
        String str2 = "用户协议";
        String str3 = "和";
        String str4 = "隐私政策";
        setSpannableString(str1, str2, str3, str4);
    }

    @Override
    public void loadData() {

    }

    /*
     *获取验证码
     * */
    public void getCode(String phone) {
        if (!RegUtils.isPhone(phone)) {
            ToastUtil.show(R.string.login_phone_error_text);
        } else {
            binding.btnCode.setEnabled(false);
            mTimer.start();
        }
    }

    /*
     * 跳转邀请码页面
     * */
    public void onClickRegister(String phone, String code) {
        start(RouteLoginConstants.LOGININVITE);
    }

    @Override
    public void initListener() {
        binding.editBidingAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    binding.loginBtn.setEnabled(true);
                } else {
                    binding.loginBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.editVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                code = s.toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    binding.loginBtn.setEnabled(true);
                } else {
                    binding.loginBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setSpannableString(String str1, String str2, String str3, String str4) {
        binding.tvProtocolsPrivate.setText("");
        SpannableString span1 = new SpannableString(str1);
        SpannableString span2 = new SpannableString(str2);
        SpannableString span3 = new SpannableString(str3);
        SpannableString span4 = new SpannableString(str4);
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.parseColor("#999999"));
        span1.setSpan(new AbsoluteSizeSpan(12, true), 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span1.setSpan(colorSpan1, 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.parseColor("#F78E06"));
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
                ds.setColor(Color.parseColor("#F78E06"));
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
                ds.setColor(Color.parseColor("#F78E06"));
                /**Remove the underline**/
                ds.setUnderlineText(false);
                ds.clearShadowLayer();
            }
        }, 0, span4.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvProtocolsPrivate.append(span1);
        binding.tvProtocolsPrivate.append(span2);
        binding.tvProtocolsPrivate.append(span3);
        binding.tvProtocolsPrivate.append(span4);
        binding.tvProtocolsPrivate.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
