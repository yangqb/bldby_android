package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.AirGoBackPriceDetailInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/19
 * time: 10:13
 * email: 694125155@qq.com
 */
/*
 * 国内往返报价搜索
 * */
public class DomesticGoBackSearchPriceRequest extends BaseAirRequest {
    public String depCity;
    public String arrCity;
    public String goDate;
    public String backDate;
    public String flightCodes;
    public String exTrack;

    @Override
    public String getAPIName() {
        return "wfSearchPrice";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("depCity", depCity).append("arrCity", arrCity)
                .append("goDate", goDate).append("backDate", backDate)
                .append("flightCodes", flightCodes).append("exTrack", exTrack));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<AirGoBackPriceDetailInfo>() {

        };
    }
}
