package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/20
 * time: 14:06
 * email: 694125155@qq.com
 */
public class InternationalBookRequest extends BaseAirRequest {
    public String accessToken;
    public String userId;
    public String source;
    public String priceKey;

    @Override
    public String getAPIName() {
        return "ntsBooking";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("source", source).append("priceKey", priceKey));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<Object>() {

        };
    }
}
