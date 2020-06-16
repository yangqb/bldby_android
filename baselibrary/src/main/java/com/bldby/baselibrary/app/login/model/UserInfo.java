package com.bldby.baselibrary.app.login.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    //手机号
    public String phone;
    //token
    public String accessToken;
    //userid
    public String userId;

    public String password;
    public String isBindPhone;//":1,
    public String isBindCode;//":0,

}
