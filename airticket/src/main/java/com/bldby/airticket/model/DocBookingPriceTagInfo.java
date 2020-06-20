package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.Map;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 13:59
 * email: 694125155@qq.com
 */
public class DocBookingPriceTagInfo implements Serializable {
    public double basePrice;
    public double viewPrice;
    public double barePrice;
    public double packagePrice;
    public String productPackageCode;
    public Map<String, String> extMap;
}
