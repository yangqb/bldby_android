package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 14:55
 * email: 694125155@qq.com
 */
public class GoBackBookingInfo implements Serializable {
    public int flightType;
    public String orderFrom;
    public String bookingTag;
    public int soloChild; //是否允许单独购买儿童票单独购买儿童票时传1，其他传0

}
