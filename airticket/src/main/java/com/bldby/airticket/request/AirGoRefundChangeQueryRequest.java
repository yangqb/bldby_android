package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.RefundChangeInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/19
 * time: 11:07
 * email: 694125155@qq.com
 */

/*
 * 国内单程退改签查询
 * */
public class AirGoRefundChangeQueryRequest extends BaseAirRequest {
    public String flightNum;
    public String cabin;
    public String dpt;
    public String arr;
    public String dptDate;
    public String dptTime;
    public String policyId;
    public String maxSellPrice;
    public String minSellPrice;
    public String printPrice;
    public String tagName;
    public boolean translate;
    public String sfid;
    public boolean needPercentTgqText;
    public String businessExt;
    public String client;
    //.params("childCabin")
    //.params("childSellPrice")


    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.JSONBody;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("flightNum", flightNum).append("cabin", cabin).append("dpt", dpt).append("arr", arr)
                .append("dptDate", dptDate).append("dptTime", dptTime).append("policyId", policyId).append("maxSellPrice", maxSellPrice)
                .append("minSellPrice", minSellPrice).append("printPrice", printPrice).append("tagName", tagName).append("translate", translate).append("sfid", sfid)
                .append("needPercentTgqText", needPercentTgqText).append("businessExt", businessExt).append("client", client));
    }

    @Override
    public String getAPIName() {
        return "tgqNewExplain";
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<RefundChangeInfo>() {

        };
    }
}
