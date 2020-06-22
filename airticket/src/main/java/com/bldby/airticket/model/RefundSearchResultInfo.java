package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:35
 * email: 694125155@qq.com
 */
public class RefundSearchResultInfo implements Serializable {
    public String reason;
    public boolean canRefund;
    public boolean canInterfaceIllRefund;
    public boolean noTicket;
    public boolean airChangeAllRefund;
    public boolean needUploadProof;
    public BaseOrderInfo baseOrderInfo;
    public List<ChangeFlightInfo> flightSegmentList;
    public ChangeRuleInfo refundRuleInfo;
    public ChangeContactInfo contactInfo;
    public String tgqViewInfoJson;
    public List<ChangeTgqReasons> tgqReasons;
    public List<RefundPassengerPriceInfoListInfo> noTicketPassengerPriceInfo;
    public boolean canExpressFeeRefund;
}
