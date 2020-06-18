package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.GoBackFlightInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/18
 * time: 16:30
 * email: 694125155@qq.com
 */
/*
 * 国内往返
 * */
public class DomesticGoBackSearchRequest extends BaseAirRequest {
    public String depCity;
    public String arrCity;
    public String goDate;
    public String backDate;
    public String exTrack;
    public int sort;//排序：1为价格最低 2为时间最早

    @Override
    public String getAPIName() {
        return "wfSearchFlight";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("depCity", depCity).append("arrCity", arrCity).append("goDate", goDate).append("backDate", backDate).append("exTrack", exTrack).append("sort", sort));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<GoBackFlightInfo>() {

        };
    }
}
