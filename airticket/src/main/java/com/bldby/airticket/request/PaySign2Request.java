package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.PayModel;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/23
 * time: 18:12
 * email: 694125155@qq.com
 */
public class PaySign2Request extends BaseRequest {
    public String accessToken;
    public String userId;
    public String appId;
    public String orderNo;
    public String channel;
    public String amount;
    public String type;
    public String gqId;
    public String passengerIds;

    @Override
    public String getAPIName() {
        return "fhwl/plane/pay";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("appId", appId)
                .append("orderNo", orderNo).append("channel", channel).append("amount", amount).append("type", type).append("gqId", gqId).append("passengerIds", passengerIds));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.POST;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<PayModel>() {

        };
    }
}
