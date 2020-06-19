package com.bldby.loginlibrary.request;

import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseLoginRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.loginlibrary.model.LoginInfo;
import com.bldby.loginlibrary.model.UserInfo;

/**
 * package name: com.bldby.loginlibrary.request
 * user: yangqinbo
 * date: 2020/6/15
 * time: 19:19
 * email: 694125155@qq.com
 */
/*
 * 验证码登录接口
 * */
public class LoginRequest extends BaseLoginRequest {
    public String mobile;
    public String mode;
    public String verifyCode;

    @Override
    public String getAPIName() {
        return "login";
    }


    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.JSONBody;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return builder.append("mobile", mobile).append("mode", mode).append("verifyCode", verifyCode);
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<LoginInfo>() {

        };
    }

}
