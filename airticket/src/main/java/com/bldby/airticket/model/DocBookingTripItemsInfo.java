package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 13:57
 * email: 694125155@qq.com
 */
public class DocBookingTripItemsInfo implements Serializable {
    public String dptCity;
    public String arrCity;
    public String dptCode;
    public String arrCode;
    public String dptPort;
    public String arrPort;
    public String dptTerminal;
    public String arrTerminal;
    public String dptDate;
    public String arrDate;
    public String dptTime;
    public String arrTime;
    public String vehicleNo;
    public String carrierName;
    public String carrierCode;
    public boolean share;
    public String shareNo;
    public int stopTimes;
    public String stopCode;
    public String stopCity;
    public String vehicleStyle;
    public String tripDuration;
    public boolean meal;
    public int tripDistance;
}
