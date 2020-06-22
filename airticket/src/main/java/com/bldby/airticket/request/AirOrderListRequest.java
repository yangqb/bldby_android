package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.AirOrderInfo;
import com.bldby.baselibrary.app.network.BaseTravelUrlRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/22
 * time: 15:40
 * email: 694125155@qq.com
 */
public class AirOrderListRequest extends BaseTravelUrlRequest {
    public String accessToken;
    public String userId;

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public String getAPIName() {
        return "planeOrder/getOrderList";
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<AirOrderInfo>() {

        };
    }
}
