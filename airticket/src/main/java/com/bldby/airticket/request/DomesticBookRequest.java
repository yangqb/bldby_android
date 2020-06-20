package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.google.gson.Gson;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/20
 * time: 13:38
 * email: 694125155@qq.com
 */
public class DomesticBookRequest extends BaseAirRequest {
    public String accessToken;
    public String userId;
    public String json;
    public String depCode;
    public String arrCode;
    public String code;
    public String date;
    public String carrier;
    public String btime;

    @Override
    public String getAPIName() {
        return "express/booking";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("vendorStr", json).append("accessToken", accessToken).append("userId", userId).append("depCode", depCode)
                .append("arrCode", arrCode).append("code", code).append("date", date).append("carrier", carrier).append("btime", btime));
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
