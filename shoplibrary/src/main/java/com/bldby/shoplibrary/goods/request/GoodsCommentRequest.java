package com.bldby.shoplibrary.goods.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BasePagingRequest;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.shoplibrary.goods.model.GoodsComment;

public class GoodsCommentRequest extends BasePagingRequest {
    @Override
    public String getAPIName() {
        return "goodsApi/goodsComment";
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<GoodsComment>() {
        };
    }
}
