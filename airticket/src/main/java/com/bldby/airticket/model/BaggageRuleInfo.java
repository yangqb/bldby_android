package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/19
 * time: 20:22
 * email: 694125155@qq.com
 */
public class BaggageRuleInfo implements Serializable {
    public String checkedBaggageRule;
    public String cabinBaggageRule;
    public String infantBaggageRule;
    public List<String> specialRules;
    public String smsContent;
}
