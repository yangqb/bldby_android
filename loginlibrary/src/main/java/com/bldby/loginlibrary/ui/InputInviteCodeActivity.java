package com.bldby.loginlibrary.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityInviteCodeBinding;
import com.bldby.loginlibrary.request.LoginRequest;

/**
 * package name: com.bldby.loginlibrary.ui
 * user: yangqinbo
 * date: 2020/6/11
 * time: 19:30
 * email: 694125155@qq.com
 */

/*
 * 填写邀请码页面
 * */
@Route(path = RouteLoginConstants.LOGININVITE)
public class InputInviteCodeActivity extends BaseUiActivity {
    private ActivityInviteCodeBinding binding;
    @Autowired
    public String phone;
    @Autowired
    public String code;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityInviteCodeBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle(getString(R.string.login_invite_title));
        setTitleBackground(R.color.white);
    }

    @Override
    public void loadData() {

    }

    public void onLogin(String inviteCode) {
        LoginRequest request = new LoginRequest();
    }

    @Override
    public void initListener() {
        binding.editInviteCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
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
}
