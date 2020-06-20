package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.DocResultBookingInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/20
 * time: 13:50
 * email: 694125155@qq.com
 */
public class DomesticGoBackBookRequest extends BaseAirRequest {
    public String accessToken;
    public String userId;
    public String bookingParamKey;

    @Override
    public String getAPIName() {
        return "dbReserve";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("bookingParamKey", bookingParamKey));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<DocResultBookingInfo>() {

        };
    }
}
