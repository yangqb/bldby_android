package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 18:38
 * email: 694125155@qq.com
 */
public class ChangeTgqReasons implements Serializable {
    public int code;
    public String msg;
    public boolean will;
    public List<RefundPassengerPriceInfoListInfo> refundPassengerPriceInfoList; //改签查询此节点返回null，无需关注
    public List<FlightAlterSearchResultWithText> changeFlightSegmentList;
}
