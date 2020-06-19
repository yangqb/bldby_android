package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.AirPriceDetailInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/19
 * time: 9:56
 * email: 694125155@qq.com
 */

/*
 * 国内单程报价搜索
 * */
public class DomesticGoSearchPriceRequest extends BaseAirRequest {
    public String dpt;
    public String arr;
    public String date;
    public String carrier;
    public String flightNum;
    public String cabin;
    public String ex_track;

    @Override
    public String getAPIName() {
        return "searchQuote";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("dpt", dpt).append("arr", arr)
                .append("date", date).append("carrier", carrier).append("flightNum", flightNum)
                .append("cabin", cabin).append("ex_track", ex_track));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<AirPriceDetailInfo>() {

        };
    }
}
