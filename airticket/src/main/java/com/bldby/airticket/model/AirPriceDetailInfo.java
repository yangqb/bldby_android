package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/19
 * time: 10:01
 * email: 694125155@qq.com
 */
public class AirPriceDetailInfo implements Serializable {
    public String date;
    public String depCode;
    public String depAirport;
    public String depTerminal;
    public String btime;
    public String arrCode;
    public String arrAirport;
    public String arrTerminal;
    public String etime;
    public String carrier;
    public String com;
    public String code;
    public boolean meal;
    public boolean zhiji;
    public String correct;
    public boolean stop;
    public int stopsNum;
    public String stopCityCode;
    public String stopCityName;
    public String stopAirportCode;
    public String stopAirportName;
    public String stopAirportFullName;
    //public String stopInfoList
    public boolean codeShare;
    public String actCode;
    public int arf;
    public int tof;
    public int distance;
    public String flightType;
    public boolean officeTicket;
    public String bairdrome;
    public String eairdrome;
    public String flightTimes;
    public List<VenDorsInfo> vendors;

}
