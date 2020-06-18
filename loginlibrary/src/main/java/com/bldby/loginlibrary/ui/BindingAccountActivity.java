package com.bldby.loginlibrary.ui;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.app.util.RegUtils;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityBidingAccountBinding;
import com.bldby.loginlibrary.request.BidingAccountRequest;
import com.bldby.loginlibrary.request.RegisterCodeRequest;

/**
 * package name: com.bldby.loginlibrary.ui
 * user: yangqinbo
 * date: 2020/6/11
 * time: 14:42
 * email: 694125155@qq.com
 */
/*
 * 微信登录绑定手机号页面
 *
 * */
@Route(path = RouteLoginConstants.BIDINGACCOUNT)
public class BindingAccountActivity extends BaseUiActivity {
    private ActivityBidingAccountBinding biding;
    private String phone = "";
    private String code = "";
    private boolean isGetCode = true; //倒计时按钮是否可以点击
    private CountDownTimer mTimer = new CountDownTimer(6000 * 10, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            biding.btnCode.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            isGetCode = true;
            biding.btnCode.setEnabled(true);
            biding.btnCode.setText(R.string.login_biding_get_code);
        }
    };

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        biding = ActivityBidingAccountBinding.inflate(layoutInflater, viewGroup, false);
        biding.setViewModel(this);
        return biding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(getString(R.string.login_biding_title_name));
        setTitleBackground(R.color.white);
    }

    @Override
    public void loadData() {

    }

    /*
     * 获取验证码
     * */
    public void getCode(String phone) {
        if (!RegUtils.isPhone(phone)) {
            ToastUtil.show(R.string.login_phone_error_text);
        } else {
            isGetCode = false;
            biding.btnCode.setEnabled(false);
            mTimer.start();
            RegisterCodeRequest codeRequest = new RegisterCodeRequest();
            codeRequest.phone = phone;
            codeRequest.type = "7";
            codeRequest.call(new ApiCallBack() {
                @Override
                public void onAPIResponse(Object response) {

                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        }
    }

    /*
     * 绑定手机号，跳转至邀请码页面
     * */
    public void onBidingAccount(String phone, String code) {
        BidingAccountRequest request = new BidingAccountRequest();
        request.phone = phone;
        request.smsCode = code;
        request.call(new ApiCallBack() {
            @Override
            public void onAPIResponse(Object response) {
                //手机号未绑定其他微信
                //AccountManager.getInstance().setLoginSuccess();
                startWith(RouteLoginConstants.LOGININVITE).withString("token", "").withString("userId", "").navigation();
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void initListener() {
        biding.editBidingAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    biding.loginBtn.setEnabled(true);
                } else {
                    biding.loginBtn.setEnabled(false);
                }

                if (RegUtils.isPhone(phone) && isGetCode) {
                    biding.btnCode.setEnabled(true);
                } else {
                    biding.btnCode.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        biding.editVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                code = s.toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    biding.loginBtn.setEnabled(true);
                } else {
                    biding.loginBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
