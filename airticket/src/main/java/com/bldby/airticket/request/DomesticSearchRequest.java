package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.SearchFlightModel;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/17
 * time: 20:35
 * email: 694125155@qq.com
 */

/*
 * 国内单程机票搜索
 * */
public class DomesticSearchRequest extends BaseAirRequest {
    public String dpt;
    public String arr;
    public String date;
    public String ex_track;

    @Override
    public String getAPIName() {
        return "searchFlight";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("dpt", dpt).append("arr", arr).append("date", date).append("ex_track", ex_track));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<SearchFlightModel>() {

        };
    }
}
