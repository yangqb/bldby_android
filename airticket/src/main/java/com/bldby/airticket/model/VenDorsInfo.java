package com.bldby.airticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * package name: com.bldby.airticket.model
 * user: yangqinbo
 * date: 2020/6/18
 * time: 20:37
 * email: 694125155@qq.com
 */
public class VenDorsInfo implements Serializable {
    public int vppr;
    public int barePrice;
    public String domain;
    public String policyType;
    public String PolicyId;
    public String discount;
    public String prtag;
    public String bprtag;
    public String cabin;
    public String cabinCount;
    public int cabinType;
    public String wrapperId;
    public String afee;
    public String it;
    public List<TGGShowInfo> tgqShowData;
    public String luggage;
    public int cardType;
    public BusinessExtMapInfo businessExtMap;
    public boolean shareShowAct;
    public String fuzzy;
    public int ptype;
    public String groupId;
    public String basePrice;
    public double zk;
    public String businessExt;
}
