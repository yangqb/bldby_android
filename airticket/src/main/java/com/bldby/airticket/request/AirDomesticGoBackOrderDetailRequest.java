package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.DomesticOrderDetailInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/22
 * time: 17:01
 * email: 694125155@qq.com
 */
public class AirDomesticGoBackOrderDetailRequest extends BaseAirRequest {
    public String accessToken;
    public String userId;
    public String orderNo;

    @Override
    public String getAPIName() {
        return "goBack/selectOrder";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("orderNo", orderNo));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<DomesticOrderDetailInfo>() {
        };
    }
}
