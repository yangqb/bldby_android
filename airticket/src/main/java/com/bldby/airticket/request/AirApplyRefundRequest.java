package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.NationalPassengerInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

import java.util.List;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:56
 * email: 694125155@qq.com
 */
public class AirApplyRefundRequest extends BaseAirRequest {
    public String callbackUrl;//后台写死了
    public String orderNo;
    public String passengerIds;
    public String refundCause;
    public String refundCauseId;
    public String amount;

    @Override
    public String getAPIName() {
        return "tgq/refundExplain";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.JSONBody;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("callbackUrl", callbackUrl).append("orderNo", orderNo).append("passengerIds", passengerIds)
                .append("refundCause", refundCause).append("refundCauseId", refundCauseId).append("amount", amount));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<NationalPassengerInfo>>() {

        };
    }
}
