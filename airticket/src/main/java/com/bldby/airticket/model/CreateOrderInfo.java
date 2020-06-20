package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 14:52
 * email: 694125155@qq.com
 */
public class CreateOrderInfo implements Serializable {

    public int id;//":0,
    public String noPayAmount;//:2350,
    public String orderNo;//202032212684",//合单订单号
    public int status;//0,
    public String validateLink;//":null,
    public boolean isSplicing;//":true,
    public List<SubOrdersInfo> subOrders;//
}
