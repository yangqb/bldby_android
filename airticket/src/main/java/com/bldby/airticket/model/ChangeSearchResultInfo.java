package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:41
 * email: 694125155@qq.com
 */
public class ChangeSearchResultInfo implements Serializable {
    public String reason;
    public boolean canChange;
    public boolean canInterfaceChange;
    public BaseOrderInfo baseOrderInfo;
    public List<ChangeFlightInfo> flightSegmentList;
    public ChangeRuleInfo changeRuleInfo;
    public ChangeContactInfo contactInfo;
    public String tgqViewInfoJson;
    public List<ChangeTgqReasons> tgqReasons;
    public int airChangeCode;
    public String airChangeDesc;
    public String passengerTypeStr;
    public boolean disabled;
    public String disableReason;
}
