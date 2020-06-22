package com.bldby.baselibrary.app.network;

import com.alibaba.fastjson.annotation.JSONField;
import com.bldby.baselibrary.core.network.BaseApiResponse;

/**
 * package name: com.bldby.baselibrary.app.network
 * user: yangqinbo
 * date: 2020/6/22
 * time: 11:37
 * email: 694125155@qq.com
 */
/*
 * 这个response只针对机票验价这一个接口，其他地方的都不要使用。
 * */
public class AirValidateResponse extends BaseApiResponse {

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

    /*
    1007是变价的code，需要提示用户机票变价
    * */
    public boolean isRequestSuccess() {
        return (code == 0 || code == 1007);
    }
}
