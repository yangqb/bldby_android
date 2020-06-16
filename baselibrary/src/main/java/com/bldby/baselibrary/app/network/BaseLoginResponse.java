package com.bldby.baselibrary.app.network;

import com.alibaba.fastjson.annotation.JSONField;
import com.bldby.baselibrary.core.network.BaseApiResponse;

/**
 * package name: com.bldby.baselibrary.app.network
 * user: yangqinbo
 * date: 2020/6/15
 * time: 19:01
 * email: 694125155@qq.com
 */
public class BaseLoginResponse extends BaseApiResponse {
    @JSONField(name = "data")
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

    public String getMessage() {
        return msg;
    }

    public boolean isRequestSuccess() {
        return (code == 0);
    }
}
