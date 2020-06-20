package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 14:03
 * email: 694125155@qq.com
 */
public class DocBookingCardTypeListInfo implements Serializable {
    public String type;
    public String name;
    public String ageLimit;
    public List<DocBookingRulesInfo> rules;
}

