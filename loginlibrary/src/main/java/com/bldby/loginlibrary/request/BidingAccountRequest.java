package com.bldby.loginlibrary.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseLoginRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;

/**
 * package name: com.bldby.loginlibrary.request
 * user: yangqinbo
 * date: 2020/6/16
 * time: 12:54
 * email: 694125155@qq.com
 */
public class BidingAccountRequest extends BaseLoginRequest {
    public String phone;
    public String smsCode;

    @Override
    public String getAPIName() {
        return "bind/bindPhone";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("phone", phone).append("smsCode", smsCode));
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference() {
        };
    }
}
