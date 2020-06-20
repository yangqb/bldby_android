package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 13:59
 * email: 694125155@qq.com
 */
public class DocBookingPriceInfo implements Serializable {
    public int ypr;
    public double dis;
    public int fuelTax;
    public int constructionFee;
    public int childFuelTax;
    public int cutPrice;
    public String prdTag;
    public String dtTag;
    public Map<String, List<DocBookingPriceTagInfo>> priceTag;
    public int avSource;
    public Map<String, String> extMap;
}
