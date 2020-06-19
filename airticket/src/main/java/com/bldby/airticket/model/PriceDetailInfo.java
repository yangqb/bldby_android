package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/19
 * time: 15:28
 * email: 694125155@qq.com
 */
public class PriceDetailInfo implements Serializable {
    public double price;
    public int arf;
    public int tof;
    public double cPrice;
    public int num; //成人数
    public int cnum;//儿童数
    public int postage;
}
