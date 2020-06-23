package com.bldby.baselibrary.app.network;

import com.alibaba.fastjson.annotation.JSONField;
import com.bldby.baselibrary.core.network.BaseApiResponse;

/**
 * Created by bch on 2020/6/4
 * 通用请求返回公共处理类
 */
public class BaseTraveUrlResponse extends BaseApiResponse {
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

    public String getMessage(){
        return msg;
    }

    public boolean isRequestSuccess() {
        return (code == 200);
    }
}
