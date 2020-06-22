package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:37
 * email: 694125155@qq.com
 */
public class ChangeRuleInfo implements Serializable {
    public int viewType;
    public boolean hasTime;
    public String tgqText;
    public String signText;
    public String childTgqMsg;
    public String timePointCharges;
    public List<TimePointChargsInfo> timePointChargesList;
    public String refundDescription;
}
