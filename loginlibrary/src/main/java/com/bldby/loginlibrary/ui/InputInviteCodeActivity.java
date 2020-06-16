package com.bldby.loginlibrary.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.app.login.model.UserInfo;
import com.bldby.baselibrary.app.util.UserInfoUtils;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityInviteCodeBinding;
import com.bldby.loginlibrary.model.UserModel;
import com.bldby.loginlibrary.request.BidingInviteCodeRequest;
import com.bldby.loginlibrary.request.UserInfoRequest;

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
        BidingInviteCodeRequest request = new BidingInviteCodeRequest();
        request.parentId = inviteCode;
        request.accessToken = token;
        request.userId = userId;
        request.call(new ApiCallBack<Boolean>() {
            @Override
            public void onAPIResponse(Boolean response) {
                getUserInfo(userId, token);
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
        request.call(new ApiCallBack<UserModel>() {
            @Override
            public void onAPIResponse(UserModel response) {
                UserInfo userInfo = UserInfoUtils.getUserInfo(InputInviteCodeActivity.this);
                userInfo.headImg = response.headImg;
                userInfo.nickName = response.nickName;
                userInfo.accountType = response.accountType;
                userInfo.parentId = response.parentId;
                userInfo.uid = response.uid;
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
                UserInfoUtils.saveUserInfo(InputInviteCodeActivity.this, userInfo);
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
