package com.bldby.centerlibrary.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.centerlibrary.model.AddressInfo;

/**
 * package name: com.bldby.centerlibrary.request
 * user: yangqinbo
 * date: 2020/6/19
 * time: 19:14
 * email: 694125155@qq.com
 */
public class AddressListRequest extends BaseRequest {
    public String accessToken;
    public String userId;

    @Override
    public String getAPIName() {
        return "fhwl/shop/selectUserAddr";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.POST;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<AddressInfo>() {
        };
    }
}
