package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/19
 * time: 10:53
 * email: 694125155@qq.com
 */
public class CustomAirPriceDetailInfo implements Serializable {
    public AirPriceDetailInfo customDocGoFlightInfo; //国内单程信息
    public VenDorsInfo customDocGoPriceInfo; //国内单程报价
    public GoBackFlightList customDocGoBackFlightInfo; //国内往返信息
    public GoBackVendors customDocGoBackPriceInfo; //国内往返报价

    public SearchInternationalFlightModel customInterFlightInfo;//国外单程和往返信息
    public InternationalPriceInfo customInterPriceInfo;//国外单程和往返报价

    public CustomFightCityInfo customFightCityInfo;
}
