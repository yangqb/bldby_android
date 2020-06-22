package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.InterOrderDetailInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/22
 * time: 16:46
 * email: 694125155@qq.com
 */
public class AirInternationalOrderDetailRequest extends BaseAirRequest {
    public String accessToken;
    public String userId;
    public String orderNo;

    @Override
    public String getAPIName() {
        return "international/orderSelect";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("orderNo", orderNo));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<InterOrderDetailInfo>() {
        };
    }
}
