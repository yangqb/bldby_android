package com.bldby.airticket.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 12:50
 * email: 694125155@qq.com
 */
public class MultipleGoSearchFightInfo implements MultiItemEntity, Serializable {
    private int type;
    public static final int DOMESTIC_TYPE = 1;
    public static final int INTERNATIONAL_TYPE = 2;

    public SearchFlightModel.FlightModel flightModel;
    public SearchInternationalFlightModel internationalFlightModel;

    public MultipleGoSearchFightInfo(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
