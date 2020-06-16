package com.bldby.loginlibrary.model;

import java.io.Serializable;

/**
 * package name: com.bldby.loginlibrary.model
 * user: yangqinbo
 * date: 2020/6/15
 * time: 19:48
 * email: 694125155@qq.com
 */
public class LoginRequestModel implements Serializable {
    public String mode;
    public String mobile;
    public String verifyCode;
}
