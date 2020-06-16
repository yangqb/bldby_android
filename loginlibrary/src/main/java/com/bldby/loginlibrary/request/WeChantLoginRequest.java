package com.bldby.loginlibrary.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseLoginRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.loginlibrary.request
 * user: yangqinbo
 * date: 2020/6/16
 * time: 12:04
 * email: 694125155@qq.com
 */
public class WeChantLoginRequest extends BaseLoginRequest {
    public String headImg;
    public String mode;
    public String nickname;
    public String openid;
    public String unionid;
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
        return null;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference() {
        };
    }
}
