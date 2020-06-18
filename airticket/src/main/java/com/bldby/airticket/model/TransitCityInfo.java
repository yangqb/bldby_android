package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 12:53
 * email: 694125155@qq.com
 */
public class TransitCityInfo implements Serializable {
    public String transitAirportCode;
    public String transitAirportName;
    public String transitCityName;
    public String transitCityCode;
    public String transitCountryName;
    public String stayTime;
    public int crossDays;
}
