package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:36
 * email: 694125155@qq.com
 */
public class ChangeFlightInfo implements Serializable {
    public String flightNo;
    public String flightCo;
    public String flightShortCo;
    public String flightPhone;
    public String dptCity;
    public String arrCity;
    public String dptPort;
    public String arrPort;
    public String dptAirport;
    public String arrAirport;
    public String dptTerminal;
    public String arrTerminal;
    public String dptDate;
    public String dptTime;
    public String arrDate;
    public String arrTime;
    public boolean stop;
    public String stopCity;
    public String stopAirport;
    public boolean isShared;
    public String realFlightNum;
}
