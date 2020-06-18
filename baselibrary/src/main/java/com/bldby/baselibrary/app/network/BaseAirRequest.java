package com.bldby.baselibrary.app.network;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.NetworkConfig;

/**
 * package name: com.bldby.baselibrary.app.network
 * user: yangqinbo
 * date: 2020/6/17
 * time: 20:37
 * email: 694125155@qq.com
 */
public abstract class BaseAirRequest extends BaseRequest {

    @Override
    public String getAPIBaseURL() {
        return NetworkConfig.getInstance().getAirBaseUrl();
    }


    @Override
    public TypeReference getResponseType() {
        return new TypeReference<BaseAirResponse>() {

        };
    }

    @Override
    public void handleError(int errorCode, String errorMsg) {
        super.handleError(errorCode, errorMsg);

    }
}
