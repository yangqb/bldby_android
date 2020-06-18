package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 12:55
 * email: 694125155@qq.com
 */
public class StopInfo implements Serializable {
    public String airportCode;
    public String airportName;
    public String cityCode;
    public String cityName;
    public String arrDate;
    public String arrTime;
    public String depDate;
    public String depTime;
    public String stopTime;
    public int crossDays;
    public String countryName;
}
