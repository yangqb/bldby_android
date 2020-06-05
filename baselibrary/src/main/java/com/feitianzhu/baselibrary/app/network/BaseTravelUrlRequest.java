package com.feitianzhu.baselibrary.app.network;

import com.alibaba.fastjson.TypeReference;
import com.feitianzhu.baselibrary.core.NetworkConfig;

/**
 * Created by bch on 2020/5/20
 */
public abstract class BaseTravelUrlRequest extends BaseRequest {
    /**
     * 设置域名 端口
     *
     * @return
     */
    @Override
    public String getAPIBaseURL() {
        return NetworkConfig.getInstance().getTicketBaseUrl();

    }

    /**
     * 设置base返回基类,进行统一处理
     *
     * @return
     */
    @Override
    public TypeReference getResponseType() {
        return new TypeReference<BaseResponse>() {
        };
    }

    @Override
    public void handleError(int errorCode, String errorMsg) {
        super.handleError(errorCode, errorMsg);

    }
}
