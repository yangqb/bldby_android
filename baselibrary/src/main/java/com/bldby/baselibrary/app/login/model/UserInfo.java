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
    public int isBindPhone;//":1,
    public int isBindCode;//":0,

}
