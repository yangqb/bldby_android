package com.feitianzhu.baselibrary.app.login.Interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.feitianzhu.baselibrary.app.login.AccountManager;
import com.feitianzhu.baselibrary.constants.RouteLoginConstants;

/**
 * priority为优先级 越小越高 登录定位第一级
 */
@Interceptor(priority = 1, name = "登录拦截器")
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        int extra = postcard.getExtra();
        if (extra == RouteLoginConstants.SHOWCHECKLOGIN && AccountManager.isLogin()) {
//TODO 去登陆页面
        }
    }

    @Override
    public void init(Context context) {

    }
}
