package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.RefundServiceInfo;
import com.bldby.baselibrary.app.network.BaseAir1Request;
import com.bldby.baselibrary.app.network.BaseAir1Response;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:09
 * email: 694125155@qq.com
 */
public class AirRefundXcdRequest extends BaseAir1Request {
    public String accessToken;
    public String userId;
    public String orderNo;

    @Override
    public String getAPIName() {
        return "refundxcdSearch";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<RefundServiceInfo>() {

        };
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("orderNo", orderNo));
    }

}
