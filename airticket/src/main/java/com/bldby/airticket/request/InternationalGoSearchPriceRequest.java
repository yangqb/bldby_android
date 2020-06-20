package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.AirInternationalPriceDetailInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/19
 * time: 10:27
 * email: 694125155@qq.com
 */
/*
 * 国际单程报价搜索
 * */
public class InternationalGoSearchPriceRequest extends BaseAirRequest {
    public String depCity;
    public String arrCity;
    public String flightCode;
    public String depDate;
    public String source;

    //.params("adultNum")
    //.params("childNum")
    //.params("cabinLevel")
    @Override
    public String getAPIName() {
        return "ntsSearchPrice";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("depCity", depCity).append("arrCity", arrCity)
                .append("flightCode", flightCode).append("depDate", depDate).append("source", source));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<AirInternationalPriceDetailInfo>() {

        };
    }
}
