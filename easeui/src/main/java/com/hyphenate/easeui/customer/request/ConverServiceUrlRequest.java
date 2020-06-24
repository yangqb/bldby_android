package com.hyphenate.easeui.customer.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseTravelUrlRequest;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.hyphenate.easeui.customer.bean.ConverzServiceListBean;

import java.util.List;

public class ConverServiceUrlRequest  extends BaseTravelUrlRequest {

    @Override
    public String getAPIName() {
        return "im/selectCS";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<ConverzServiceListBean>>() {
        };
    }
}
