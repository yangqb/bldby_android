package com.feitianzhu.baselibrary.app.login;


import com.feitianzhu.baselibrary.app.RxCodeConstants;
import com.feitianzhu.baselibrary.app.login.model.UserInfo;
import com.feitianzhu.baselibrary.core.rxbus.RxBus;
import com.orhanobut.hawk.Hawk;

public class AccountManager {
    private static AccountManager accountManager = new AccountManager();
    private static final String LOGINKEY = "LOGINKEY";
    private static final String LOGINKEYUSERINFO = "LOGINKEYUSERINFO";
    private UserInfo userInfo = new UserInfo();

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        accountManager.init();
        return accountManager;
    }

    private void init() {
        this.userInfo = Hawk.get(LOGINKEYUSERINFO);
    }


    public static boolean isLogin() {
        return Hawk.get(LOGINKEY, false);
    }

    /**
     * 判断是否需要登录，并且在没有登录的情况下跳转登录页面
     *
     * @return
     */
    public static boolean shouldShowLogin() {
        if (isLogin()) {
            //TODO 跳转登录页面
            return false;
        }
        return true;
    }

    public boolean isVip() {
        return true;
    }

    public void setLoginSuccess(UserInfo userInfo) {
        this.userInfo = userInfo;
        Hawk.put(LOGINKEY, true);
        Hawk.put(LOGINKEYUSERINFO, userInfo);
        //发出登录通知
        RxBus.getDefault().post(RxCodeConstants.LOGINSTATUSCHANGE, true);
    }

    public void updataLoginInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        Hawk.put(LOGINKEYUSERINFO, userInfo);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * 退出登录
     */
    public void logOut() {
        Hawk.put(LOGINKEY, false);
        Hawk.delete(LOGINKEYUSERINFO);
        //发出退出登录通知
        RxBus.getDefault().post(RxCodeConstants.LOGINSTATUSCHANGE, false);
    }
}
