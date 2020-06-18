package com.bldby.loginlibrary.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    //手机号
    public String phone;
    //token
    public String accessToken;
    //userid
    public String userId;

    public int isBindPhone;//":1,
    public int isBindCode;//":0,

    public String canWd;//":1是否可以提现（0否 1是） ,
    public String paypass;// (string, optional): 支付密码 ,
    public String personSign; //(string, optional): 个性签名 ,

    public UserData userInfo;

    public String headImg;//":"http://bldby-pro.oss-cn-beijing.aliyuncs.com/user/default_headimg.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=7779392034789&Signature=LY6fSJKVZ1k1xB1znu8JQmHMNQs%3D",
    public String nickName;//":"便利大本营0321",
    public int accountType;//":0,账户类型，参考NetBodyEnum枚举类 ,
    public String parentId;//":"6HLRAC",邀请人邀请码 ,
    public int clientType;//":5,客户端类型（1：android，2：ios，3：其他） ,
    public double balance;//":0,余额
    public String openid;// (string, optional): 微信平台用户唯一标识 ,
    public String password;// (string, optional): 登陆密码(保留字段) ,
    public int subordinateCount;// (integer, optional): 发展会员的数量 ,
    public double totalConsume;//":0,总消费金额 ,
    public String inviteCode;//":"S7JMAP",用户自己的邀请码 ,
    public double totalPoints;//":0,共盈利 ,
    public int isFrozen;//":0,是否冻结（1：是，0否） ,
    public String registerDate;//":"2020-06-16 10:07:13"
    public String unionid;// (string, optional): 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。

    public static class UserData {
        public String uid;//":6,用户id
        public String id;//":4,
        public String canWd;//":1是否可以提现（0否 1是） ,
        public String paypass;// (string, optional): 支付密码 ,
        public String personSign; //(string, optional): 个性签名 ,
    }
}