package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseAir2Request;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/20
 * time: 16:51
 * email: 694125155@qq.com
 */
public class DeletePassengerRequest extends BaseAir2Request {
    public String accessToken;
    public String userId;
    public String id;

    @Override
    public String getAPIName() {
        return "delPassenger";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.POST;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("id", id));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<Object>() {

        };
    }
}
