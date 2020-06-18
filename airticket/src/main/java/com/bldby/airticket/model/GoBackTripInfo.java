package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 12:53
 * email: 694125155@qq.com
 */
public class GoBackTripInfo implements Serializable {
    public List<TransitCityInfo> transitCities;//中转城市列表
    public List<FlightSegmentInfo> flightSegments;//航程中的所有航段信息列表，按照出发顺序排列
    public String duration;
    public int crossDays;
}
