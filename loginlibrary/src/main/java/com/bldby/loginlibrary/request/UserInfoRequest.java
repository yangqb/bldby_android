package com.bldby.loginlibrary.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseLoginRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.loginlibrary.model.UserInfo;

/**
 * package name: com.bldby.loginlibrary.request
 * user: yangqinbo
 * date: 2020/6/16
 * time: 15:15
 * email: 694125155@qq.com
 */

/*
 * 用户信息
 * */
public class UserInfoRequest extends BaseLoginRequest {
    public String userId;
    public String accessToken;

    @Override
    public String getAPIName() {
        return "user/getUserInfo";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("userId", userId).append("accessToken", accessToken));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<UserInfo>() {

        };
    }
}
