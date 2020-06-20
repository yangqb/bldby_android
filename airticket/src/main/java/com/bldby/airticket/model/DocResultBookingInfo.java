package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 13:57
 * email: 694125155@qq.com
 */
public class DocResultBookingInfo implements Serializable {
    public int type;
    public List<DocBookingTripInfos> tripInfos;
    public DocBookingPassengerContact passengerContact;
    public DocBookingExpressInfo expressInfo;
    public List<DocBookingCardTypeListInfo> cardTypeList;
    public String bookingTag;
}
