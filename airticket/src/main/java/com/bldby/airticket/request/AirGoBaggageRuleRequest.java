package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.BaggageRuleInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/19
 * time: 20:12
 * email: 694125155@qq.com
 */

/*
 * 国内单程行李托运规则
 * */
public class AirGoBaggageRuleRequest extends BaseAirRequest {
    public String airlineCode;
    public String cabin;
    public String depCode;
    public String arrCode;
    public String luggage;
    public String saleDate;
    public String depDate;

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("airlineCode", airlineCode).append("cabin", cabin).append("depCode", depCode)
                .append("arrCode", arrCode).append("luggage", luggage).append("saleDate", saleDate).append("depDate", depDate));
    }

    @Override
    public String getAPIName() {
        return "baggagerules";
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<BaggageRuleInfo>() {
        };
    }
}
