package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 17:06
 * email: 694125155@qq.com
 */
public class DocOrderDetail implements Serializable {
    public String orderNo;
    public String status;
    public String pnr;
    public String siblingOrderNo;
    public String siblingOrderStatus;
    public String orderSelfStatus;
    public String parentOrderStatus;
    public String parentOrderNo;
    public DocChildrenOrderInfo childrenOrderInfo;
    public String agentLastTicketTime;
    // public Object cashBack;返现信息（无需关注）
}
