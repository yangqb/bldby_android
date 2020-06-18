package com.bldby.airticket.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 16:16
 * email: 694125155@qq.com
 */
public class MultiGoBackFlightInfo implements MultiItemEntity, Serializable {
    public static final int DOMESTIC = 2;
    public static final int INTERNATIONAL = 3;
    private int type;
    public GoBackFlightList domesticFlight;
    public SearchInternationalFlightModel internationalFlight;
    public double price;

    public MultiGoBackFlightInfo(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}