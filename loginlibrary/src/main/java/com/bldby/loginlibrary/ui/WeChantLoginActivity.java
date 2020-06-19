package com.bldby.loginlibrary.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.R;
import com.bldby.loginlibrary.databinding.ActivityLoginWechantBinding;
import com.bldby.loginlibrary.model.LoginInfo;
import com.bldby.loginlibrary.model.UserInfo;
import com.bldby.loginlibrary.model.WXUserInfo;
import com.bldby.loginlibrary.request.WeChantLoginRequest;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * package name: com.bldby.loginlibrary.ui
 * user: yangqinbo
 * date: 2020/6/12
 * time: 9:34
 * email: 694125155@qq.com
 */

/*
 *
 * 微信登录页面
 * */
@Route(path = RouteLoginConstants.LOGINWECHANT)
public class WeChantLoginActivity extends BaseUiActivity {
    private ActivityLoginWechantBinding binding;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityLoginWechantBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        setTitleBackground(R.color.white);
    }

    /*
     * 微信登录授权
     * */
    public void weChantAuth(View view) {
        if (!binding.checkbox.isChecked()) {
            ToastUtil.show(R.string.login_agree_protocols);
            return;
        }
        if (UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.WEIXIN)) {
            reqWeiXin();
        } else {
            ToastUtil.show(R.string.login_install_wx);
        }
    }

    public void reqWeiXin() {
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                WXUserInfo wxUserInfo = new WXUserInfo();
                wxUserInfo.city = map.get("city");
                wxUserInfo.country = map.get("country");
                wxUserInfo.headimgurl = map.get("iconurl");
                wxUserInfo.nickname = map.get("name");
                wxUserInfo.openid = map.get("openid");
                //wxUserInfo.privilege
                wxUserInfo.province = map.get("province");
                //wxUserInfo.sex = 1;
                wxUserInfo.unionid = map.get("unionid");
                UserInfo userInfo = AccountManager.getInstance().getUserInfo();
                userInfo.wxUserInfo = wxUserInfo;
                AccountManager.getInstance().setLoginSuccess(userInfo);
                wxLogin(wxUserInfo);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                ToastUtil.show(R.string.login_auth_error + throwable.getMessage());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                ToastUtil.show(R.string.login_cancel);
            }
        });
    }

    public void wxLogin(WXUserInfo wxUserInfo) {
        WeChantLoginRequest weChantLoginRequest = new WeChantLoginRequest();
        weChantLoginRequest.headImg = wxUserInfo.headimgurl;
        weChantLoginRequest.mode = "2";
        weChantLoginRequest.nickname = wxUserInfo.nickname;
        weChantLoginRequest.openid = wxUserInfo.openid;
        weChantLoginRequest.unionid = wxUserInfo.unionid;
        weChantLoginRequest.call(new ApiCallBack<LoginInfo>() {
            @Override
            public void onAPIResponse(LoginInfo response) {
                //未绑定手机号
                if (response.isBindPhone == 0) {
                    start(RouteLoginConstants.BIDINGACCOUNT);
                } else {
                    //此微信已绑定过注册的账号直接登录
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    /*
     * 手机号登录
     * */
    public void onPhoneLogin(View view) {
        finish();
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
