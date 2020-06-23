package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.NationalPassengerInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.baselibrary.core.util.MathUtils;

import java.util.List;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/23
 * time: 18:00
 * email: 694125155@qq.com
 */
public class AirChangeApplyRequest extends BaseAirRequest {
    public String applyRemarks;
    public String cabinCode;
    public String callbackUrl;
    public String changeCauseId;
    public String childExtraPrice;
    public String childUseFee;
    public String endTime;
    public String flightNo;
    public String gqFee;
    public String orderNo;
    public String passengerIds;
    public String startDate;
    public String startTime;
    public String uniqKey;
    public String upgradeFee;

    @Override
    public String getAPIName() {
        return "tgq/applyChange";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("applyRemarks", applyRemarks).append("cabinCode", cabinCode).append("callbackUrl", callbackUrl).append("changeCauseId", changeCauseId)
                .append("childExtraPrice", childExtraPrice).append("childUseFee", childUseFee).append("endTime", endTime).append("flightNo", flightNo).append("gqFee", gqFee).append("orderNo", orderNo)
                .append("passengerIds", passengerIds).append("startDate", startDate).append("startTime", startTime).append("uniqKey", uniqKey).append("upgradeFee", upgradeFee));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.JSONBody;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<NationalPassengerInfo>>() {

        };
    }
}
