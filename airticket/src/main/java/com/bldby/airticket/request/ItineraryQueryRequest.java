package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/23
 * time: 17:35
 * email: 694125155@qq.com
 */
public class ItineraryQueryRequest extends BaseAirRequest {
    public String orderNo;

    @Override
    public String getAPIName() {
        return "itinerary/search";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("orderNo", orderNo));
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
