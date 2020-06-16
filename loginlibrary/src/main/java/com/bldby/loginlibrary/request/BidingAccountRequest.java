package com.bldby.loginlibrary.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseLoginRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.loginlibrary.request
 * user: yangqinbo
 * date: 2020/6/16
 * time: 10:44
 * email: 694125155@qq.com
 */
public class BidingAccountRequest extends BaseLoginRequest {
    public String parentId;
    public String accessToken;
    public String userId;

    @Override
    public String getAPIName() {
        return "bind/bindParent";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("parentId", parentId));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference() {
        };
    }
}
