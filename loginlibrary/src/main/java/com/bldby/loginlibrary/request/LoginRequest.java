package com.bldby.loginlibrary.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseLoginRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;

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
    public String jsonStr;

    @Override
    public String getAPIName() {
        return "login";
    }


    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder);
    }

    @Override
    public TypeReference getDatatype() {
        return null;
    }

}
