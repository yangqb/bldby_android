package com.bldby.baselibrary.app.network;

import com.alibaba.fastjson.annotation.JSONField;
import com.bldby.baselibrary.core.network.BaseApiResponse;

/**
 * package name: com.bldby.baselibrary.app.network
 * user: yangqinbo
 * date: 2020/6/17
 * time: 20:38
 * email: 694125155@qq.com
 */
public class BaseAirResponse extends BaseApiResponse {
    @JSONField(name = "result")
    public Object data;

    @JSONField(name = "code")
    public int code = -1;
    //
    @JSONField(name = "message")
    public String msg;
//
//    @JSONField(name = "ret")
//    public int ret;

    public Object getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getResult() {
        return msg;
    }

    public boolean isRequestSuccess() {
        return (code == 0);
    }
}
