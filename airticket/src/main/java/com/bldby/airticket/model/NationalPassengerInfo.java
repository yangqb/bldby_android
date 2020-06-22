package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:34
 * email: 694125155@qq.com
 */
public class NationalPassengerInfo implements Serializable {
    public long id;
    public String name;
    public String cardType;
    public String cardNum;
    public String ticketNum;
    public String birthday;
    public int gender;
    public RefundSearchResultInfo refundSearchResult;// Object
    public RefundApplyResultInfo refundApplyResult;//Object
    public ChangeSearchResultInfo changeSearchResult;// Object
    public ChangeApplyResultInfo changeApplyResult;// Object
}
