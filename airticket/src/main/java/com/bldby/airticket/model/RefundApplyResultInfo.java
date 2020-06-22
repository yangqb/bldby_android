package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:40
 * email: 694125155@qq.com
 */
public class RefundApplyResultInfo implements Serializable {
    public boolean success;
    public String reason;
    public boolean volunteer;
    public boolean noTicket;
}