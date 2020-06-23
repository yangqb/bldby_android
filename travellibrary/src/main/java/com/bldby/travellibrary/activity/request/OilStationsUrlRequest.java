package com.bldby.travellibrary.activity.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseTravelRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;
import com.bldby.travellibrary.activity.model.OilListBean;

import java.util.List;

public class OilStationsUrlRequest extends BaseTravelRequest {
    public String longitude;
    public String latitude;
 /*   public String accessToken;
    public String userId;*/
    public int kilometre;
    public int oilNum;
    public int pageSize;
    public int currentPage;

    @Override
    public String getAPIName() {
        return "fleetin/getOilStations";
    }

    public OilStationsUrlRequest(String longitude, String latitude, int kilometre, int oilNum, int pageSize, int currentPage) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.kilometre = kilometre;
        this.oilNum = oilNum;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("longitude", longitude)
                .append("latitude", latitude).append("kilometre", kilometre)
                .append("oilNum", oilNum).append("pageSize", pageSize)
                .append("currentPage", currentPage)
              /*  .append("userId", userId)
                .append("accessToken",accessToken)*/

        );
    }


    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.GET;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<List<OilListBean>>() {
        };
    }
}

