package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:39
 * email: 694125155@qq.com
 */
public class BasePassengerPriceInfo implements Serializable {
    public boolean disabled;
    public String disableReason;
    public long passengerId;
    public String passengerName;
    public String cardNum;
    public String passengerTypeStr;
    public int ticketPrice;
    public int constructionFee;
    public int fuelTax;
}
