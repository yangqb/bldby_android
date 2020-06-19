package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/19
 * time: 10:34
 * email: 694125155@qq.com
 */
public class AirInternationalPriceDetailInfo implements Serializable {
    public GoBackTripInfo goTrip;
    public GoBackTripInfo backTrip;
    public List<InternationalPriceInfo> priceInfo;
}
