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
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.app.util.RegUtils;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityRegisterBinding;
import com.bldby.loginlibrary.model.BaseUserInfo;
import com.bldby.loginlibrary.request.LoginRequest;
import com.bldby.loginlibrary.request.RegisterCodeRequest;
import com.bldby.loginlibrary.request.UserInfoRequest;
import com.bldby.loginlibrary.model.UserInfo;
import com.bldby.loginlibrary.util.UserInfoUtils;


/**
 * package name: com.bldby.loginlibrary.ui
 * user: yangqinbo
 * date: 2020/6/11
 * time: 18:39
 * email: 694125155@qq.com
 */
/*
 * 注册登录页面
 * */
@Route(path = RouteLoginConstants.REGISTER)
public class RegisterActivity extends BaseUiActivity {
    private ActivityRegisterBinding binding;
    private String phone = "";
    private String code = "";
    private boolean isGetCode = true; //倒计时按钮是否可以点击

    private CountDownTimer mTimer = new CountDownTimer(6000 * 10, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            binding.btnCode.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            isGetCode = true;
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
        binding.btnCode.setEnabled(false);
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
     * 微信登录跳转
     * */
    public void onWeChantLogin(View view) {
        start(RouteLoginConstants.LOGINWECHANT);
    }

    /*
     *获取验证码
     * */
    public void getCode(String phone) {
        if (!RegUtils.isPhone(phone)) {
            ToastUtil.show(R.string.login_phone_error_text);
        } else {
            isGetCode = false;
            binding.btnCode.setEnabled(false);
            mTimer.start();
            RegisterCodeRequest codeRequest = new RegisterCodeRequest();
            codeRequest.phone = phone;
            codeRequest.type = "1";
            codeRequest.call(new ApiCallBack<Boolean>() {
                @Override
                public void onAPIResponse(Boolean response) {

                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        }
    }

    /*
     * 跳转邀请码页面
     * */
    public void onClickRegister(String phone, String code) {
        if (!binding.checkbox.isChecked()) {
            ToastUtil.show(R.string.login_agree_protocols);
            return;
        }

        LoginRequest request = new LoginRequest();
        request.mobile = phone;
        request.mode = "1";
        request.verifyCode = code;
        request.call(new ApiCallBack<UserInfo>() {
            @Override
            public void onAPIResponse(UserInfo response) {
                if (response.isBindCode == 0) {//未填写过邀请码
                    AccountManager.getInstance().updataLoginInfo(response);
                    startWith(RouteLoginConstants.LOGININVITE).withString("token", response.accessToken).withString("userId", response.userId).navigation();
                } else {
                    AccountManager.getInstance().setLoginSuccess(response);
                    getUserInfo(response.userId, response.accessToken);
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    /*
     * 获取用户信息保存
     * */
    public void getUserInfo(String userId, String accessToken) {
        UserInfoRequest request = new UserInfoRequest();
        request.userId = userId;
        request.accessToken = accessToken;
        request.call(new ApiCallBack<BaseUserInfo>() {
            @Override
            public void onAPIResponse(BaseUserInfo response) {
                UserInfo userInfo = AccountManager.getInstance().getUserInfo();
                userInfo.headImg = response.headImg;
                userInfo.nickName = response.nickName;
                userInfo.accountType = response.accountType;
                userInfo.parentId = response.parentId;
                userInfo.clientType = response.clientType;
                userInfo.balance = response.balance;
                userInfo.totalConsume = response.totalConsume;
                userInfo.phone = response.phone;
                userInfo.inviteCode = response.inviteCode;
                userInfo.totalPoints = response.totalPoints;
                userInfo.isFrozen = response.isFrozen;
                userInfo.registerDate = response.registerDate;
                userInfo.openid = response.openid;
                userInfo.subordinateCount = response.subordinateCount;
                userInfo.unionid = response.unionid;
                userInfo.canWd = response.userInfo.canWd;
                userInfo.paypass = response.userInfo.paypass;
                userInfo.personSign = response.userInfo.personSign;
                AccountManager.getInstance().updataLoginInfo(userInfo);
                //UserInfoUtils.saveUserInfo(RegisterActivity.this, userInfo);
                start(RouteAirConstants.MAIN);
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {
                start(RouteAirConstants.MAIN);
            }
        });
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
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code) && RegUtils.isPhone(phone)) {
                    binding.loginBtn.setEnabled(true);
                } else {
                    binding.loginBtn.setEnabled(false);
                }
                if (RegUtils.isPhone(phone) && isGetCode) {
                    binding.btnCode.setEnabled(true);
                } else {
                    binding.btnCode.setEnabled(false);
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
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code) && RegUtils.isPhone(phone)) {
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
