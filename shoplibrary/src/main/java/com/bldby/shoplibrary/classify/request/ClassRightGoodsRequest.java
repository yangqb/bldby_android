package com.bldby.shoplibrary.classify.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.shoplibrary.classify.bean.ClassRightGoodsBean;

import java.util.List;

public class ClassRightGoodsRequest extends BaseRequest {
    private int id;
    @Override
    public String getAPIName() {
        return "categoryApi/getListById";
    }

    public ClassRightGoodsRequest(int id) {
        this.id = id;
    }
    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder
                .append("id", id)

        );
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<ClassRightGoodsBean>(){

        };
    }
}
