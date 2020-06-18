package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 20:38
 * email: 694125155@qq.com
 */
public class TGGShowInfo implements Serializable {
    public int tgqFrom;
    public String returnRule;
    public String changeRule;
    public boolean canRefund;
    public boolean airlineTgq;
    public List<TgqPointChargesInfo> tgqPointCharges;
}
