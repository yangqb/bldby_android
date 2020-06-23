package com.bldby.baselibrary.app.network;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * package name: com.bldby.baselibrary.app.network
 * user: yangqinbo
 * date: 2020/6/23
 * time: 9:36
 * email: 694125155@qq.com
 */
public class BaseAir2Response extends BaseResponse {
    @JSONField(name = "data")
    public Object data;

    @JSONField(name = "code")
    public int code = -1;
    //
    @JSONField(name = "msg")
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
