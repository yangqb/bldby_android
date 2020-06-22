package com.bldby.loginlibrary.model;

public class LoginInfo {

    //token
    public String accessToken;
    //userid
    public String userId;

    public int isBindPhone;//":1,
    public int isBindCode;//":0,  //此字段只在登录时候判断是否进行过绑定邀请码，是否绑定成功过邀请码根据个人信息parentId是否为空判断
}
