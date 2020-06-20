package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 13:59
 * email: 694125155@qq.com
 */
public class DocBookingPolicyInfo implements Serializable {
    public int xcd;
    public int cardType;
    public int maxAge;
    public int minAge;
    public boolean childBuyAdult;
    public boolean soloChild;
    public boolean shareShowAct;
}
