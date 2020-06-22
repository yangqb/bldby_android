package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:36
 * email: 694125155@qq.com
 */
public class BaseOrderInfo implements Serializable {
    public int status;
    public String statusDesc;
    public boolean showNotWork;
    public int distributeType;
}
