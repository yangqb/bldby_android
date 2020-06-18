package com.bldby.airticket.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 20:37
 * email: 694125155@qq.com
 */
public class MultiPriceInfo implements MultiItemEntity, Serializable {
    public static final int DOMESTIC_TYPE = 1;
    public static final int INTERNATIONAL_TYPE = 2;
    public static final int DOMESTIC_GO_BACK_TYPE = 3;
    private int type;
    public VenDorsInfo venDorsInfo;
    public InternationalPriceInfo internationalPriceInfo;
    public GoBackVendors goBackVendors;

    public MultiPriceInfo(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
