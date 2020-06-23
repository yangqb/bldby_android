package com.bldby.shoplibrary.seach.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BasePagingRequest;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.shoplibrary.seach.model.GoodsSeachModel;
import com.lzy.okgo.model.HttpHeaders;

import java.util.List;

public class GoodsSeachRequest extends BasePagingRequest {

    public String keyWord = "";
    //    排序规则 0 销量降序 1 价格降序 2 价格升序
    public int sort = 1;

    @Override
    public String getAPIName() {
        return "goodsApi/searchGoods";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.JSONBody;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder
                .append("keyWord", keyWord)
                .append("sort", sort)
        );
    }

    @Override
    public TypeReference getPageDatatype() {
        return new TypeReference<List<GoodsSeachModel>>() {
        };
    }


}
