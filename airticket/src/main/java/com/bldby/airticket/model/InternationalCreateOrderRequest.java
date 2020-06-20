package com.bldby.airticket.model;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 15:16
 * email: 694125155@qq.com
 */
public class InternationalCreateOrderRequest extends BaseAirRequest {
    public String accessToken;
    public String userId;
    public String bookingResult;
    public String bookingTagKey;
    public String passengersStr;
    public String contact;
    public String xcd;

    @Override
    public String getAPIName() {
        return "ntsCreateOrder";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("bookingResult", bookingResult)
                .append("bookingTagKey", bookingTagKey).append("passengersStr", passengersStr).append("contact", contact).append("xcd", xcd));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.POST;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<Object>() {

        };
    }
}
