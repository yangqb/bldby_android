package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 20:39
 * email: 694125155@qq.com
 */
public class BusinessExtMapInfo implements Serializable {
    public boolean supportChildBuyAdult;
    public boolean supportChild;
    public int splicingType;
    public double childPrice;
    public String childCabin;
    public double childByAdultPrice;
    public String childByAdultCabin;
}