package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 20:40
 * email: 694125155@qq.com
 */
public class InternationalPriceInfo implements Serializable {
    public String packName;
    public double price;
    public double tax;
    public String taxType;
    public double cPrice;
    public double cTax;
    public String cTaxType;
    public String cabin;
    public String cabinLevel;
    public String priceKey;
    public String domain;
    public String productTag;
    public LuggageDirectData luggageDirectData;
    public double zk;
}
