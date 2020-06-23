package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.airticket.model.CreateOrderInfo;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

import java.net.PortUnreachableException;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/20
 * time: 14:31
 * email: 694125155@qq.com
 */
/*
 * 国内单程生单
 * */
public class DomesticCreateOrderRequest extends BaseAirRequest {
    public String accessToken;
    public String userId;
    public int receiverType;
    public String contact;
    public String contactMob;
    //public String contactPreNum;后台已经写死+86
    public String cardNo;
    public String bookingResult;
    public boolean needXcd;
    public String address;
    public String passengerStr;
    public String receiverTitle;
    public String taxpayerId;
    public String sjr;
    public String sjrPhone;

    @Override
    public String getAPIName() {
        return "createOrder";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        if (receiverType != -1) {
            builder.append("receiverType", receiverType);
        }
        return builder.append("accessToken", accessToken).append("userId", userId).append("contact", contact).append("contactMob", contactMob)
                .append("cardNo", cardNo).append("bookingResult", bookingResult).append("needXcd", needXcd)
                .append("address", address).append("passengerStr", passengerStr).append("receiverTitle", receiverTitle).append("taxpayerId", taxpayerId).append("sjr", sjr).append("sjrPhone", sjrPhone);
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.JSONBody;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<CreateOrderInfo>() {

        };
    }
}
