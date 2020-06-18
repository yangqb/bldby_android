package com.bldby.travellibrary.activity.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseTravelUrlRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.travellibrary.activity.model.OilOrederBean;

import java.util.List;

public class OilOrderUrlRequest extends BaseTravelUrlRequest {
    public int limitNum;
    public int curPage;
    public String phone;
    public String accessToken;
    public String userId;
    @Override
    public String getAPIName() {
        return "fleetin/getOrderInfo";
    }
    public OilOrderUrlRequest(int limitNum, int curPage, String phone) {
        this.limitNum = limitNum;
        this.curPage = curPage;
        this.phone = phone;
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder
                .append("limitNum",limitNum)
                .append("accessToken",accessToken)
                .append("userId",userId)
                .append("curPage",curPage).append("phone",phone));
    }



    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<OilOrederBean>>(){};
    }
}
