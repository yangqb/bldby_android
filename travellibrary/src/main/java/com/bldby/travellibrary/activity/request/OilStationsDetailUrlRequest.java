package com.bldby.travellibrary.activity.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseTravelUrlRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.travellibrary.activity.model.OilStationsDetailBean;

import java.util.List;

public class OilStationsDetailUrlRequest extends BaseTravelUrlRequest {

    public String gasIds;
    public String phone;
    public String accessToken;
    public String userId;
    @Override
    public String getAPIName() {
        return "fleetin/getOilStationsDetail";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder
                .append("gasIds", gasIds)
                .append("accessToken", accessToken)
                .append("userId", userId)
                .append("phone", phone));
    }
    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<OilStationsDetailBean>>() {
        };
    }
}
