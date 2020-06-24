package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.RefundChangeInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

import java.util.List;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/19
 * time: 11:30
 * email: 694125155@qq.com
 */
public class AirGoBackRefundChangeQueryRequest extends BaseAirRequest {
    public String carrier;
    public String depCode;
    public String arrCode;
    public String goDate;
    public String backDate;
    public String outCabin;
    public String retCabin;
    public String businessExts;
    public String goFlightNum;
    public String backFlightNum;
    public String policyId;
    public double price;
    public String client;
    public double barePrice;
    public String tagName;

    @Override
    public String getAPIName() {
        return "tgqNewBack";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.JSONBody;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("carrier", carrier).append("depCode", depCode).append("arrCode", arrCode).append("goDate", goDate)
                .append("backDate", backDate).append("outCabin", outCabin).append("retCabin", retCabin).append("businessExts", businessExts)
                .append("goFlightNum", goFlightNum).append("backFlightNum", backFlightNum).append("policyId", policyId).append("price", price).append("client", client)
                .append("barePrice", barePrice).append("tagName", tagName));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<RefundChangeInfo>>() {

        };
    }
}
