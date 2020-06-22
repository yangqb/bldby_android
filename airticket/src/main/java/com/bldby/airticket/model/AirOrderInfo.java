package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/22
 * time: 15:56
 * email: 694125155@qq.com
 */
public class AirOrderInfo implements Serializable {
    public List<AirOrderModel> all;
    public List<AirOrderModel> refundOrUpdateList;
    public List<AirOrderModel> waiPayList;
    public List<AirOrderModel> waitTicketedList;
    public List<AirOrderModel> ticketedList;
    public int waitTicketedCount;
    public int ticketedCount;
    public int refundOrUpdateCount;
    public int waiPayCount;

    public static class AirOrderModel implements Serializable {
        public double amount;// (number, optional): 实际支付金额 ,
        public String backCityInfo;// (string, optional): 回程出发和到达城市 ,
        public String backFlightNum;// (string, optional): 航班号(返程) ,
        public String backFlyTime;// (string, optional): 行程时间(返程) ,
        public String channel;//(string, optional): 支付渠道 ,
        public String createTime;// (string, optional): 订单创建时间 ,
        public double discount;//(number, optional): 平台折扣 ,
        public String flightNum;//(string, optional): 航班号 ,
        public String goCityInfo;// (string, optional): 去程出发和到达城市 ,
        public String goFlyTime;// (string, optional): 行程时间(去程) ,
        public int isGoBack;// (integer, optional): 是否是往返机票 0否 1是 ,
        public int isSplicing;//(integer, optional): 是否是合单 ,
        public double noPayAmount;// (integer, optional): 未支付订单金额 ,
        public String orderNo;//(string, optional): 平台订单号 ,
        public String payTime;// (string, optional): 订单支付时间 ,
        public String remarks;// (string, optional): 备注 ,
        public String sourceOrderNo;//(string, optional): 去哪儿订单号 ,
        public int status;//(integer, optional): 订单状态 ,
        public int type;//(integer, optional): 机票类型：1国内；2往返；3国际 ,
        public int userId;// (integer, optional): 用户id
        public String dptAirport;//":"大兴国际机场",
        public String arrAirport;//":"虹桥机场",
        public String dptTerminal;//":"",
        public String arrTerminal;//":"T2",
        public String backDptAirport;//":"虹桥机场",
        public String backArrAirport;//":"大兴国际机场",
        public String backDptTerminal;//":"T2",
        public String backArrTerminal;//":""
    }
}
