package com.bldby.shoplibrary.classify.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.shoplibrary.classify.bean.ClassLeftGoodsBean;

import java.util.List;

public class ClassGoodsLeftRequset extends BaseRequest {
    @Override
    public String getAPIName() {
        return "categoryApi/getOneCategoryList";
    }
    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<ClassLeftGoodsBean>>(){

        };
    }
}
