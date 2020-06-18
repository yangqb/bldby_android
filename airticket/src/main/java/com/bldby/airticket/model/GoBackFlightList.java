package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 16:16
 * email: 694125155@qq.com
 */
public class GoBackFlightList implements Serializable {
    public int tof;//":"0",
    public int arf;//":"100",
    public GoBackFlight go;
    public GoBackFlight back;
    public GoBackPack pack;
    public String flightCodes;//":"HU7609_HU7602",
    public int minBarePrice;//":"540"
    public double zk;

}
