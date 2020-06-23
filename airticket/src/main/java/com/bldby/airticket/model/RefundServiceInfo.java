package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 20:03
 * email: 694125155@qq.com
 */
public class RefundServiceInfo implements Serializable {
    public int code;
    public String message;
    public String createTime;
    public boolean canApply;
    public String orderNo;
    public String invoiceType;
    public int invoiceCode;
    public int expressFee;
}
