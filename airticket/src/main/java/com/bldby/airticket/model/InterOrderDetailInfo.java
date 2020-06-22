package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 16:53
 * email: 694125155@qq.com
 */
public class InterOrderDetailInfo implements Serializable {
    public String createTime;
    public int orderStatus;
    public String orderNo;
    public InterContactInfo contactInfo;
    public List<InterOrderDetailPayInfo> payInfo;
    public InterOrderDetailFlightInfo flightInfo;
    public InterOrderDetailPassengerInfo passengerInfo;
    public InterOrderDetailXcdInfo xcdInfo;
    public InterOrderDetailExtInfo extInfo;
    public List<Object> serviceInfo;
    public List<InterOrderDetailProductInfo> productInfo;
}
