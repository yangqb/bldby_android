package com.bldby.shoplibrary.goods.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BasePagingRequest;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.shoplibrary.goods.model.GoodsComment;

import java.util.List;

public class GoodsCommentRequest extends BasePagingRequest {
    public int spuId;

    @Override
    public String getAPIName() {
        return "goodsApi/goodsComment";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("spuId", spuId));
    }

    @Override
    public TypeReference getPageDatatype() {
        return new TypeReference<List<GoodsComment>>() {
        };
    }

}
