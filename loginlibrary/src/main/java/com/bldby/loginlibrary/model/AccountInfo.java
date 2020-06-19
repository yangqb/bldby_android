package com.bldby.loginlibrary.model;

public class AccountInfo {

    /**
     * uid : 13
     * phone : 15011462696
     * nickName : 便利大本营2696
     * headImg : http://bldby-pro.oss-cn-beijing.aliyuncs.com/user/default_headimg.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=7779392034789&Signature=LY6fSJKVZ1k1xB1znu8JQmHMNQs%3D
     * password : null
     * parentId : null
     * balance : 0.0
     * totalPoints : 0.0
     * totalConsume : 0.0
     * clientType : 5
     * registerDate : 2020-06-18 11:31:45
     * isFrozen : 0
     * accountType : 0
     * subordinateCount : null
     * openid : null
     * unionid : null
     * inviteCode : N6IZAC
     * userInfo : {"id":11,"uid":13,"paypass":null,"canWd":1,"personSign":null}
     * isDelete : 0
     */
    public int uid;
    public String phone;
    public String nickName;//":"便利大本营0321",
    public String headImg;//":"http://bldby-pro.oss-cn-beijing.aliyuncs.com/user/default_headimg.png?OSSAccessKeyId=LTAI4GHEkb4SUqKsjoLMcw1d&Expires=7779392034789&Signature=LY6fSJKVZ1k1xB1znu8JQmHMNQs%3D",
    public String password;// (string, optional): 登陆密码(保留字段) ,
    public String parentId;//":"6HLRAC",邀请人邀请码 ,
    public double balance;//":0,余额
    public double totalPoints;//":0,共盈利 ,
    public double totalConsume;//":0,总消费金额 ,
    public int clientType;//":5,客户端类型（1：android，2：ios，3：其他） ,
    public String registerDate;//":"2020-06-16 10:07:13"
    public int isFrozen;//":0,是否冻结（1：是，0否） ,
    public int accountType;//":0,账户类型，参考NetBodyEnum枚举类 ,
    public int subordinateCount;// (integer, optional): 发展会员的数量 ,
    public String openid;// (string, optional): 微信平台用户唯一标识 ,
    public String inviteCode;//":"S7JMAP",用户自己的邀请码 ,
    public String unionid;// (string, optional): 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
    public UserInfoBean userInfo;
    public int isDelete;



    public static class UserInfoBean {
        /**
         * id : 11
         * uid : 13
         * paypass : null
         * canWd : 1
         * personSign : null
         */

        public String uid;//":6,用户id
        public String id;//":4,
        public String canWd;//":1是否可以提现（0否 1是） ,
        public String paypass;// (string, optional): 支付密码 ,
        public String personSign; //(string, optional): 个性签名 ,
    }
}
