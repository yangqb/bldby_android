package com.bldby.loginlibrary.ui;

import android.databinding.DataBindingUtil;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityLoginBinding;

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
}
