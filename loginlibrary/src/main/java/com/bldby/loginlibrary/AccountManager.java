package com.bldby.loginlibrary;


import com.alibaba.android.arouter.launcher.ARouter;
import com.bldby.baselibrary.app.RxCodeConstants;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.core.rxbus.RxBus;
import com.bldby.loginlibrary.model.UserInfo;
import com.orhanobut.hawk.Hawk;

/**
 * 登录信息状态管理类
 */
public class AccountManager {
    private static AccountManager accountManager = new AccountManager();
    //保存登录状态的可以
    private static final String LOGINKEY = "LOGINKEY";
    //保存登录信息的可以
    private static final String LOGINKEYUSERINFO = "LOGINKEYUSERINFO";
    private UserInfo userInfo = new UserInfo();

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        accountManager.init();
        return accountManager;
    }

    //已经登陆打开APP获取用户信息
    private void init() {
        this.userInfo = Hawk.get(LOGINKEYUSERINFO,new UserInfo());
    }

    /**
     * 是否登陆
     *
     * @return
     */
    public static boolean isLogin() {
        return Hawk.get(LOGINKEY, false);
//        return true;
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
        ARouter.getInstance().build(RouteLoginConstants.LOGINMAIN).navigation();
        return true;
    }

    /**
     * 是不是vip
     *
     * @return
     */
    public boolean isVip() {
        return true;
    }

    /**
     * 登录成功设置用户信息
     *
     * @param userInfo
     */
    public void setLoginSuccess(UserInfo userInfo) {
        this.userInfo = userInfo;
        Hawk.put(LOGINKEY, true);
        Hawk.put(LOGINKEYUSERINFO, userInfo);
        //发出登录通知
        RxBus.getDefault().post(RxCodeConstants.LOGINSTATUSCHANGE, true);
    }

    /**
     * 修改用户信息
     *
     * @param userInfo
     */
    public void updataLoginInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        Hawk.put(LOGINKEYUSERINFO, userInfo);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
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
