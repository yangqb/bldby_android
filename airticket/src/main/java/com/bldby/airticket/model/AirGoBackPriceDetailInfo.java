package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/19
 * time: 10:14
 * email: 694125155@qq.com
 */
public class AirGoBackPriceDetailInfo implements Serializable {
    public List<GoBackVendors> packVendors;
    public GoBackFlight go;
    public GoBackFlight back;
}
