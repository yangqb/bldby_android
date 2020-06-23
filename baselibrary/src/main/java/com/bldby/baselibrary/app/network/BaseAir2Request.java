package com.bldby.baselibrary.app.network;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.NetworkConfig;

/**
 * package name: com.bldby.baselibrary.app.network
 * user: yangqinbo
 * date: 2020/6/23
 * time: 9:35
 * email: 694125155@qq.com
 */
public abstract class BaseAir2Request extends BaseRequest {
    @Override
    public String getAPIBaseURL() {
        return NetworkConfig.getInstance().getAirBaseUrl();
    }


    @Override
    public TypeReference getResponseType() {
        return new TypeReference<BaseAir2Response>() {

        };
    }

    @Override
    public void handleError(int errorCode, String errorMsg) {
        super.handleError(errorCode, errorMsg);

    }
}
