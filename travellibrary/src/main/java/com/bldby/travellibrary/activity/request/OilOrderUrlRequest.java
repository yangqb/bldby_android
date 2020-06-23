package com.bldby.travellibrary.activity.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseTravelUrlRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.travellibrary.activity.model.OilOrederBean;

import java.util.List;

public class OilOrderUrlRequest extends BaseTravelUrlRequest {
    public int pageSize;
    public int currentPage;
    public String phone;
    @Override
    public String getAPIName() {
        return "fleetin/getOrderInfo";
    }
    public OilOrderUrlRequest(int pageSize, int currentPage, String phone) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.phone = phone;
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder
                .append("limitNum",pageSize)
                .append("curPage",currentPage).append("phone",phone));
    }



    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<OilOrederBean>>(){};
    }
}
