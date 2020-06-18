package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.SearchInternationalFlightModel;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

import java.util.List;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/18
 * time: 16:54
 * email: 694125155@qq.com
 */

/*
 * 国际往返
 * */
public class InternationGoBackSearchRequest extends BaseAirRequest {
    public String depCity;
    public String arrCity;
    public String depDate;
    public String retDate;//往返必填
    public String source;
    public String adultNum;//成人数量
    public String childNum;//儿童数量
    public String cabinLevel;//舱位等级
    public String sort;//排序：1为价格最低 2为时间最早

    @Override
    public String getAPIName() {
        return "ntsSearchFlight";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("depCity", depCity).append("arrCity", arrCity).append("depDate", depDate).append("retDate", retDate).append("source", source));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<SearchInternationalFlightModel>>() {

        };
    }
}
