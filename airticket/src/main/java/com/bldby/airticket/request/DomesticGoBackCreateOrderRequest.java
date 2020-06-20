package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.DocGoBackCreateOrderInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/20
 * time: 15:04
 * email: 694125155@qq.com
 */
public class DomesticGoBackCreateOrderRequest extends BaseAirRequest {
    public String accessToken;
    public String userId;
    public String bookingInfo;
    public String passengerInfos;
    public String contact;
    public String tripItems;
    public String reimbursement;
    //public String userClientInfo;

    @Override
    public String getAPIName() {
        return "fxOrder";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.POST;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("bookingInfo", bookingInfo)
                .append("passengerInfos", passengerInfos).append("contact", contact).append("tripItems", tripItems).append("reimbursement", reimbursement));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<DocGoBackCreateOrderInfo>() {

        };
    }
}
