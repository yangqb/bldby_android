package com.bldby.baselibrary.app.network;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.NetworkConfig;

/**
 * package name: com.bldby.baselibrary.app.network
 * user: yangqinbo
 * date: 2020/6/15
 * time: 18:57
 * email: 694125155@qq.com
 */
public abstract class BaseLoginRequest extends BaseRequest {
    @Override
    public String getAPIBaseURL() {
        return NetworkConfig.getInstance().getBaseUrl();
    }

    @Override
    public TypeReference getResponseType() {
        return new TypeReference<BaseLoginResponse>() {

        };
    }

    @Override
    public void handleError(int errorCode, String errorMsg) {
        super.handleError(errorCode, errorMsg);

    }
}
