package com.bldby.shoplibrary.goods.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseRequest;

public class GoodsCommentRequest extends BaseRequest {
    @Override
    public String getAPIName() {
        return "goodsApi/goodsComment";
    }

    @Override
    public TypeReference getDatatype() {
        return null;
    }
}
