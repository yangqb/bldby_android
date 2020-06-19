package com.bldby.loginlibrary.model;

import java.io.Serializable;

public class UserInfo implements Serializable {

    //登录的信息
    public LoginInfo loginInfo;
    public AccountInfo accountInfo;
    public WXUserInfo wxUserInfo;


    //":1是否可以提现（0否 1是） ,
    public String getCanWd() {
        return accountInfo.userInfo.canWd;
    }

    // (string, optional): 支付密码 ,
    public String getPaypass() {
        return accountInfo.userInfo.paypass;
    }

    //(string, optional): 个性签名 ,
    public String getPersonSign() {
        return accountInfo.userInfo.personSign;
    }
}