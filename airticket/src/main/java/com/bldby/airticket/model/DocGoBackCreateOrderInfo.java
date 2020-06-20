package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/20
 * time: 15:11
 * email: 694125155@qq.com
 */
public class DocGoBackCreateOrderInfo implements Serializable {
    public int Code;
    public String noPayAmount;
    public List<DocGoBackCreateOrderModel> orders;
}
