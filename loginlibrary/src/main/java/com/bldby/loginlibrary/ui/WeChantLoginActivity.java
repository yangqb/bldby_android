package com.bldby.loginlibrary.ui;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteAirConstants;
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
        String str1 = "已阅读并同意";
        String str2 = "用户协议";
        String str3 = "和";
        String str4 = "隐私政策";
        setSpannableString(str1, str2, str3, str4);
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
                    finish();
                } else {
                    //此微信已绑定过注册的账号直接登录
                    finish();
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
        start(RouteLoginConstants.REGISTER);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

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
}
