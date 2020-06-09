package com.feitianzhu.baselibrary.app.login;


import com.feitianzhu.baselibrary.app.login.model.UserInfo;

public class AccountManager {
    private static AccountManager accountManager = new AccountManager();
    private UserInfo userInfo = new UserInfo();

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        return accountManager;
    }

    public void setLoginSuccess(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public static boolean isLogin() {
        return false;
    }

    /**
     * 判断是否需要登录，并且在没有登录的情况下跳转登录页面
     *
     * @return
     */
    public static boolean shouldShowLogin() {
        if (isLogin()) {
            return false;
        }
        return true;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
