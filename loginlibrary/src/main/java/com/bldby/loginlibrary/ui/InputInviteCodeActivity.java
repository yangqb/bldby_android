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
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityInviteCodeBinding;
import com.bldby.loginlibrary.model.LoginRequestModel;
import com.bldby.loginlibrary.request.BidingAccountRequest;
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
    public String token;
    @Autowired
    public String userId;

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

    /*
     * 填写邀请码登录
     * */
    public void onLogin(String inviteCode) {
        BidingAccountRequest request = new BidingAccountRequest();
        request.parentId = inviteCode;
        request.accessToken = token;
        request.userId = userId;
        request.call(new ApiCallBack() {
            @Override
            public void onAPIResponse(Object response) {

            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
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
