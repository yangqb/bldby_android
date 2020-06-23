package com.bldby.airticket.model;

import java.io.Serializable;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/23
 * time: 17:06
 * email: 694125155@qq.com
 */
public class AskForResultInfo implements Serializable {
    /*
    true：已经预生单，可以直接支付；
     false：包邮情况为false；
    * */
    public boolean needPay;
}
