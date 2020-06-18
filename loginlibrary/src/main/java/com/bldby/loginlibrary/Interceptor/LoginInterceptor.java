package com.bldby.loginlibrary.Interceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.loginlibrary.AccountManager;

/**
 * priority为优先级 越小越高 登录定位第一级
 */
@Interceptor(priority = 1, name = "登录拦截器")
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        int extra = postcard.getExtra();
        if (extra == RouteLoginConstants.SHOWCHECKLOGIN && !AccountManager.isLogin()) {
//            ARouter.getInstance().build(RouteLoginConstants.LOGININVITE).navigation();
            ARouter.getInstance().build(RouteLoginConstants.REGISTER).navigation();
            // 处理完成，交还控制权
            callback.onInterrupt(new RuntimeException("未登录"));
        } else {
            callback.onContinue(postcard);

        }
    }

    @Override
    public void init(Context context) {

    }
}
