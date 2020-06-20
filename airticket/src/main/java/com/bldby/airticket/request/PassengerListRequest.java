package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.PassengerModel;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

import java.util.List;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/20
 * time: 16:36
 * email: 694125155@qq.com
 */
public class PassengerListRequest extends BaseRequest {
    public String accessToken;
    public String userId;

    @Override
    public String getAPIName() {
        return "getPassengersList";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<PassengerModel>>() {

        };
    }
}
