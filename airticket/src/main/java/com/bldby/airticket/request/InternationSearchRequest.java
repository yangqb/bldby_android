package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.SearchInternationalFlightModel;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.BaseApiRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

import java.util.List;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/18
 * time: 15:31
 * email: 694125155@qq.com
 */

/*
 * 国际单程
 * */
public class InternationSearchRequest extends BaseAirRequest {
    public String depCity;
    public String arrCity;
    public String depDate;
    public String retDate;//往返必填
    public String source;
    public String adultNum;//成人数量
    public String childNum;//儿童数量
    public String cabinLevel;//舱位等级

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
        return super.appendParams(builder.append("depCity", depCity).append("arrCity", arrCity).append("depDate", depDate).append("source", source));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<SearchInternationalFlightModel>>() {

        };
    }
}
