package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.CustomCityModel;
import com.bldby.baselibrary.app.network.BaseTravelUrlRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

import java.util.List;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/17
 * time: 16:28
 * email: 694125155@qq.com
 */
public class SelectCityRequest extends BaseTravelUrlRequest {
    public int flag; //1国内地点  2国际地点

    @Override
    public String getAPIName() {
        return "getPlace";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return builder.append("flag", flag);
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<CustomCityModel>>() {

        };
    }
}
