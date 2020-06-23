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
 * date: 2020/6/23
 * time: 17:45
 * email: 694125155@qq.com
 */

/*
 * 改签查询
 * */
public class AirChangeQueryRequest extends BaseAirRequest {
    public String orderNo;
    public String changeDate;

    @Override
    public String getAPIName() {
        return "tgq/changeSearch";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("orderNo", orderNo).append("changeDate", changeDate));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<NationalPassengerInfo>>() {

        };
    }
}
