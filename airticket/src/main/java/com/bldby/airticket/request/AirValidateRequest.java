package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.NetworkConfig;
import com.bldby.baselibrary.app.network.AirValidateResponse;
import com.bldby.baselibrary.app.network.BaseAirRequest;
import com.bldby.baselibrary.app.network.BaseAirResponse;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/22
 * time: 11:56
 * email: 694125155@qq.com
 */
public class AirValidateRequest extends BaseRequest {
    public String accessToken;
    public String userId;
    public String orderNo;
    public String pmCode;
    public String bankCode;

    @Override
    public String getAPIBaseURL() {
        return NetworkConfig.getInstance().getAirBaseUrl();
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("accessToken", accessToken).append("userId", userId).append("orderNo", orderNo).append("pmCode", pmCode).append("bankCode", bankCode));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public String getAPIName() {
        return "payValidate";
    }

    @Override
    public TypeReference getResponseType() {
        return new TypeReference<AirValidateResponse>() {

        };
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<Object>() {

        };
    }


    @Override
    public void handleError(int errorCode, String errorMsg) {
        super.handleError(errorCode, errorMsg);
    }
}
