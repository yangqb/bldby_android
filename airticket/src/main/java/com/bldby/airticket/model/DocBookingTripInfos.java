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
public class DocBookingTripInfos implements Serializable {
    public List<DocBookingTripItemsInfo> tripItems;
    public DocBookingTransferInfo transferInfo;
    public List<DocBookingClientBookingResultInfo> clientBookingResult;
    //public List<> baggageRuleInfos;
    public DocBookingTipsInfo tips;
}
