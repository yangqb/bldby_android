package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.AskForResultInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/23
 * time: 16:55
 * email: 694125155@qq.com
 */
public class AirRefundAskForRequest extends BaseAirRequest {
    public String userId;
    public String orderNo;
    public String receiptTitle;
    public String receiptTitleTypeCode;
    public String receiverCity;
    public String receiverDistrict;
    public String receiverName;
    public String receiverPhone;
    public String receiverProvince;
    public String receiverStreet;
    public String reimburseType;
    public String taxpayerId;

    @Override
    public String getAPIName() {
        return "askFor";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("userId", userId).append("orderNo", orderNo).append("receiptTitle", receiptTitle).append("receiptTitleTypeCode", receiptTitleTypeCode)
                .append("receiverCity", receiverCity).append("receiverDistrict", receiverDistrict).append("receiverName", receiverName).append("receiverPhone", receiverPhone).append("receiverProvince", receiverProvince)
                .append("receiverStreet", receiverStreet).append("reimburseType", reimburseType).append("taxpayerId", taxpayerId));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.JSONBody;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<AskForResultInfo>() {

        };
    }
}
