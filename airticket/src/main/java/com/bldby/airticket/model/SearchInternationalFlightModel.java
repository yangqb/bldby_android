package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 12:51
 * email: 694125155@qq.com
 */
public class SearchInternationalFlightModel implements Serializable {
    public double zk;
    public String flightCode;
    public double price;
    public double tax;
    public String taxType;
    public double cprice;
    public double vctax;
    public String cTaxType;
    public GoBackTripInfo goTrip;
    public GoBackTripInfo backTrip;
    public String cabinLevel;
    public String cabin;
}

