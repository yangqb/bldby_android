package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 17:05
 * email: 694125155@qq.com
 */
public class DomesticOrderDetailInfo implements Serializable {
    public long expiresDate;//":1585895495000,
    public long nowTimeStamp;//":1590719796249
    public String message;
    public double discount;
    public DocOrderDetail detail;
    public DocOrderDetailXcdInfo xcd;
    public DocOrderDetailOtherInfo other;
    public DocOrderDetailContacterInfo contacterInfo;
    public List<DocOrderDetailPassengersInfo> passengers;
    public List<DocOrderDetailFlightInfo> flightInfo;
    public List<DocOrderDetailPassengerTypesInfo> passengerTypes;
}
