package com.bldby.shoplibrary.goods.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.shoplibrary.goods.model.GoodsComment;
import com.bldby.shoplibrary.goods.model.GoodsDetailModel;

public class GoodsDetailRequest extends BaseRequest {
    private int spuId;

    @Override
    public String getAPIName() {
        return "goodsApi/goodDetail";
    }

    public GoodsDetailRequest(int spuId) {
        this.spuId = spuId;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("spuId", spuId));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<GoodsDetailModel>() {
        };
    }
}
