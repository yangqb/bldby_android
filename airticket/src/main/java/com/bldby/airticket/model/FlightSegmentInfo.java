package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 12:54
 * email: 694125155@qq.com
 */
public class FlightSegmentInfo implements Serializable {
    public String carrierCode;
    public String carrierShortName;
    public String carrierFullName;
    public String flightNum;
    public String depAirportCode;
    public String depAirportName;
    public String depTerminal;
    public String arrAirportName;
    public String arrAirportCode;
    public String arrTerminal;
    public String depCityName;
    public String arrCityName;
    public String depCityCode;
    public String arrCityCode;
    public String depDate;
    public String depTime;
    public String arrDate;
    public String arrTime;
    public String codeShareStatus;
    public String mainCode;
    public String mainCarriercode;
    public String mainCarrierShortName;
    public String mainCarrierFullName;
    public String planeTypeCode;
    public String planeTypeName;
    public String duration;
    public int crossDays;
    public int stops;
    public List<StopInfo> stop;
}
